package com.ad.core;

import com.ad.handler.Processor;
import com.ad.helper.VersionException;
import com.ad.model.TradeDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TradeExecutorThread implements Runnable{

    private static final Logger logger = LoggerFactory.getLogger(TradeExecutorThread.class);

    private final Processor<TradeDetails> tradeProcessor;
    private final TaskReader<TradeDetails> tradeTaskHandler;

    private boolean stop = false;

    public TradeExecutorThread(Processor<TradeDetails> tradeProcessor, TaskReader<TradeDetails> tradeTaskHandler){
        this.tradeProcessor = tradeProcessor;
        this.tradeTaskHandler = tradeTaskHandler;
    }

    @Override
    public void run() {

        while(!stop){
            try {
                TradeDetails trade = tradeTaskHandler.getTask();
                tradeProcessor.process(trade);
            }catch (VersionException exp){
                logger.error(exp.getMessage());
            } catch (InterruptedException e) {
                logger.error("Error in reading the task", e);
                Thread.currentThread().interrupt();
            }
        }

    }

    public void stopThread(){
        stop = true;
    }

}
