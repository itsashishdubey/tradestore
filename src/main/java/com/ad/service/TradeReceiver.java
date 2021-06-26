package com.ad.service;

import com.ad.core.TaskWriter;
import com.ad.model.TradeDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeReceiver implements Receiver<TradeDetails> {

    private static final Logger logger = LoggerFactory.getLogger(TradeReceiver.class);

    @Autowired
    private TaskWriter<TradeDetails> tradeTaskHandler;

    @Override
    public void accept(TradeDetails tradeDetails) {

        try {
            logger.info("[Input] Trade received {}", tradeDetails);
            tradeTaskHandler.addTask(tradeDetails);
        } catch (InterruptedException e) {
            logger.error("Error occurred in writing for trade {}",tradeDetails, e);
            Thread.currentThread().interrupt();
        }
    }
}
