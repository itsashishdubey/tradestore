package com.ad.core;

import com.ad.model.TradeDetails;
import com.ad.model.TradeDetailsDataTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.BlockingQueue;

import static org.mockito.Mockito.when;

public class TradeTaskHandlerTest {
    @Mock
    BlockingQueue<TradeDetails> tradeQueue;
    @InjectMocks
    TradeTaskHandler tradeTaskHandler;

    TradeDetails td;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        td = TradeDetailsDataTest.getValidTrades().get(0);
    }

    @Test
    public void testGetTask() throws InterruptedException {
        tradeTaskHandler.addTask(td);
        when(tradeQueue.take()).thenReturn(td);

        TradeDetails result = tradeTaskHandler.getTask();
        Assert.assertNotNull(result);
    }

}
