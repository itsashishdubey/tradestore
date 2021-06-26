package com.ad.handler;

import com.ad.model.TradeDetails;
import com.ad.model.TradeDetailsDataTest;
import com.ad.store.TradeStore;
import com.ad.validator.DateValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class ExpiryCleanupTest {
    @Mock
    Logger logger;
    @Mock
    TradeStore tradeStore;
    @Mock
    DateValidator<TradeDetails> tradeDateValidator;
    @InjectMocks
    ExpiryCleanup expiryCleanup;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testClean()  {
        List<TradeDetails> trades = TradeDetailsDataTest.getValidTrades();
        when(tradeStore.getAllTrades()).thenReturn(trades);
        when(tradeDateValidator.validate(any())).thenReturn(false);

        expiryCleanup.clean();
        Assert.assertTrue(trades.get(0).getExpired());

    }
}