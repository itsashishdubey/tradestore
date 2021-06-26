package com.ad.core;

import com.ad.handler.Processor;
import com.ad.model.TradeDetails;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.util.concurrent.ExecutorService;

import static org.mockito.Mockito.*;

public class TradeExecutorThreadPoolTest {
    @Mock
    Logger logger;
    @Mock
    ExecutorService threadPool;
    @Mock
    TaskReader<TradeDetails> tradeTaskHandler;
    @Mock
    Processor<TradeDetails> tradeProcessor;
    @InjectMocks
    TradeExecutorThreadPool tradeExecutorThreadPool;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testThreadPool() {
        tradeExecutorThreadPool.start();
        Assert.assertTrue(tradeExecutorThreadPool.isRunning());
        tradeExecutorThreadPool.stop();
        Assert.assertFalse(tradeExecutorThreadPool.isRunning());
    }

}
