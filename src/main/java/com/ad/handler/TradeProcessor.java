package com.ad.handler;

import com.ad.helper.MaturityDateException;
import com.ad.model.TradeDetails;
import com.ad.store.TradeStore;
import com.ad.validator.DateValidator;
import com.ad.validator.VersionValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class TradeProcessor implements Processor<TradeDetails> {

    private static final Logger logger = LoggerFactory.getLogger(TradeProcessor.class);

    @Autowired
    private ILockManager lockManager;

    @Autowired
    private TradeStore tradeStore;

    @Autowired
    private DateValidator<TradeDetails> tradeMaturityDateValidator;

    @Autowired
    private VersionValidator<TradeDetails> tradeVersionValidator;

    /**
     * Processes the TradeDetails
     * 1. Validate the trade maturity date
     * 2. Validate the received version is higher than existing one
     * 3. If all validation are successful, then add trade to store
     * @param tradeDetails
     */
    @Override
    public void process(TradeDetails tradeDetails){
        if(!tradeMaturityDateValidator.validate(tradeDetails)){
            throw new MaturityDateException("[Validation failed] Trade is matured "+ tradeDetails);
        }
        Long tradeId = tradeDetails.getTradeId();
        ReentrantLock lock = lockManager.getLockByTradeId(tradeId);
        try {

            lock.lock();
            TradeDetails existingTrade = tradeStore.getTrade(tradeId);
            if (existingTrade != null && !tradeVersionValidator.validate(tradeDetails, existingTrade)) {
                logger.error("[Validation failed] Older version received for TradeId {}, received version {}, existing version {}", tradeDetails.getTradeId()
                        ,tradeDetails.getVersion(), existingTrade.getVersion());
            } else {
                tradeDetails.setCreatedDate(LocalDate.now());
                tradeDetails.setExpired(false);
                tradeStore.addTrade(tradeDetails);
                logger.info("[Added] Trade stored {}", tradeDetails);
            }
        }finally {
            lock.unlock();
        }
    }
}
