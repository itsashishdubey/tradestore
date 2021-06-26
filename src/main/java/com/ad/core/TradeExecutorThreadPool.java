package com.ad.core;

import com.ad.handler.Processor;
import com.ad.model.TradeDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@Component
public class TradeExecutorThreadPool implements SmartLifecycle {

    private static final Logger logger = LoggerFactory.getLogger(TradeExecutorThreadPool.class);

    private ExecutorService threadPool;
    private boolean isRunning = false;

    @Autowired
    private TaskReader<TradeDetails> tradeTaskHandler;

    @Autowired
    private Processor<TradeDetails> tradeProcessor;

    private static final Integer THREAD_COUNT = 10;

    @Override
    public void start() {

        threadPool = Executors.newFixedThreadPool(THREAD_COUNT);
        IntStream.range(0, THREAD_COUNT).forEach(i -> {
                    TradeExecutorThread tradeThread = new TradeExecutorThread(tradeProcessor,
                            tradeTaskHandler);
                    threadPool.execute(tradeThread);
                }
        );
        isRunning = true;
        logger.info("Created Thread pool of {} threads", THREAD_COUNT);
    }

    @Override
    public void stop() {
        isRunning = false;
        threadPool.shutdown();
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

}
