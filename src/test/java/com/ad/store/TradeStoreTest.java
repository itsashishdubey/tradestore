package com.ad.store;

import com.ad.model.TradeDetails;
import com.ad.model.TradeDetailsDataTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collection;
import java.util.Map;

public class TradeStoreTest {
    @Mock
    Map<Long, TradeDetails> tradeIdTradeDetailsMap;
    @InjectMocks
    TradeStore tradeStore;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddTrade() {
        TradeDetails td = TradeDetailsDataTest.getValidTrades().get(0);
        tradeStore.addTrade(td);

        TradeDetails storedTrade = tradeStore.getTrade(td.getTradeId());
        Assert.assertSame(td, storedTrade);

        Collection<TradeDetails> storedTrades = tradeStore.getAllTrades();
        Assert.assertSame(1, storedTrades.size());
    }
}