package com.ad.handler;

import com.ad.model.TradeDetails;
import com.ad.store.TradeStore;
import com.ad.validator.DateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ExpiryCleanup class checks the trade and set the expiry to true if it is matured
 */
@Component
public class ExpiryCleanup implements Cleanup{

    private static final Logger logger = LoggerFactory.getLogger(ExpiryCleanup.class);

    @Autowired
    private TradeStore tradeStore;

    @Autowired
    private DateValidator<TradeDetails> tradeDateValidator;


    @Override
    public void clean() {
        logger.info("Setting the matured trades to expiry");
        tradeStore.getAllTrades().forEach( t ->{
            if(!tradeDateValidator.validate(t))
                t.setExpired(true);
        });
    }
}
