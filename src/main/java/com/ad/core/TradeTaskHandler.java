package com.ad.core;

import com.ad.model.TradeDetails;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class TradeTaskHandler implements TaskReader<TradeDetails>, TaskWriter<TradeDetails>{
    private final BlockingQueue<TradeDetails> tradeQueue = new LinkedBlockingQueue<>();


    @Override
    public TradeDetails getTask() throws InterruptedException {
        return tradeQueue.take();
    }

    @Override
    public void addTask(TradeDetails obj) throws InterruptedException {
        tradeQueue.put(obj);
    }
}
