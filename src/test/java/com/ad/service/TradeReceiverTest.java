package com.ad.service;

import com.ad.core.TaskWriter;
import com.ad.model.TradeDetails;
import com.ad.model.TradeDetailsDataTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import static org.mockito.Mockito.*;

public class TradeReceiverTest {
    @Mock
    Logger logger;
    @Mock
    TaskWriter<TradeDetails> tradeTaskHandler;
    @InjectMocks
    TradeReceiver tradeReceiver;

    TradeDetails td;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        td = TradeDetailsDataTest.getValidTrades().get(0);
    }

    @Test
    public void testAccept() throws InterruptedException{
        tradeReceiver.accept(td);
        verify(tradeTaskHandler).addTask(td);
    }

    @Test
    public void testAcceptWithInterruptedException() throws InterruptedException{
        doThrow(new InterruptedException()).when(tradeTaskHandler).addTask(td);
        tradeReceiver.accept(td);
        verify(tradeTaskHandler).addTask(td);
    }
}