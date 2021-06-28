package com.ad.handler;

import com.ad.helper.VersionException;
import com.ad.model.TradeDetails;
import com.ad.model.TradeDetailsDataTest;
import com.ad.store.TradeStore;
import com.ad.validator.DateValidator;
import com.ad.validator.VersionValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.util.concurrent.locks.ReentrantLock;

import static org.mockito.Mockito.*;

public class TradeProcessorTest {
    @Mock
    Logger logger;
    @Mock
    ILockManager lockManager;
    @Mock
    TradeStore tradeStore;
    @Mock
    DateValidator<TradeDetails> tradeMaturityDateValidator;
    @Mock
    VersionValidator<TradeDetails> tradeVersionValidator;
    @InjectMocks
    TradeProcessor tradeProcessor;

    private TradeDetails newTrade;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        newTrade = TradeDetailsDataTest.getValidTrades().get(0);
    }

    @Test
    public void testProcess()  {
        when(lockManager.getLockByTradeId(newTrade.getTradeId())).thenReturn(new ReentrantLock());
        when(tradeStore.getTrade(newTrade.getTradeId())).thenReturn(null);
        when(tradeMaturityDateValidator.validate(newTrade)).thenReturn(true);
        tradeProcessor.process(newTrade);
        verify(tradeStore).addTrade(newTrade);
        Assert.assertFalse(newTrade.getExpired());
    }

    @Test
    public void testProcessVersionException()  {
        TradeDetails td = TradeDetailsDataTest.getInvalidTrades().get(0);
        when(tradeMaturityDateValidator.validate(td)).thenReturn(false);
        tradeProcessor.process(td);
        verify(lockManager, times(0)).getLockByTradeId(td.getTradeId());
    }

    @Test(expected = VersionException.class)
    public void testProcessPreviousVersion()  {

        TradeDetails existingTrade = TradeDetailsDataTest.getValidTrades().get(0);
        existingTrade.setVersion(newTrade.getVersion() - 1);
        when(lockManager.getLockByTradeId(newTrade.getTradeId())).thenReturn(new ReentrantLock());
        when(tradeStore.getTrade(newTrade.getTradeId())).thenReturn(existingTrade);
        when(tradeVersionValidator.validate(newTrade, existingTrade)).thenReturn(false);
        when(tradeMaturityDateValidator.validate(newTrade)).thenReturn(true);
        tradeProcessor.process(newTrade);
    }
}