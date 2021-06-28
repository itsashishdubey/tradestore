package com.ad.core;

import com.ad.handler.Processor;
import com.ad.helper.VersionException;
import com.ad.model.TradeDetails;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import static org.mockito.Mockito.*;

public class TradeExecutorThreadTest {
    @Mock
    Logger logger;
    @Mock
    Processor<TradeDetails> tradeProcessor;
    @Mock
    TaskReader<TradeDetails> tradeTaskHandler;
    @InjectMocks
    TradeExecutorThread tradeExecutorThread;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRun() throws Exception {
        when(tradeTaskHandler.getTask()).thenReturn(new TradeDetails());

        new Thread(() -> {
            tradeExecutorThread.run();
        }).start();
        tradeExecutorThread.stopThread();
        verify(tradeProcessor, atMost(1)).process(any(TradeDetails.class));

    }

    @Test
    public void testRunThrowsMaturityDateException() throws Exception {
        TradeDetails td = new TradeDetails();
        when(tradeTaskHandler.getTask()).thenReturn(td);
        doThrow(new VersionException("msg")).when(tradeProcessor).process(td);

        new Thread(() -> {
            tradeExecutorThread.run();
        }).start();

        tradeExecutorThread.stopThread();
        verify(tradeProcessor,atMost(1)).process(any(TradeDetails.class));

    }

}