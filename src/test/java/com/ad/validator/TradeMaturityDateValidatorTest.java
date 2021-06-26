package com.ad.validator;

import com.ad.model.TradeDetails;
import com.ad.model.TradeDetailsDataTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import static org.mockito.Mockito.*;

public class TradeMaturityDateValidatorTest {
    @Mock
    Logger logger;
    @InjectMocks
    TradeMaturityDateValidator tradeMaturityDateValidator;

    TradeDetails td;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        td = TradeDetailsDataTest.getInvalidTrades().get(0);
    }

    @Test
    public void testValidate()  {
        boolean result = tradeMaturityDateValidator.validate(td);
        Assert.assertFalse( result);

        td.setMaturityDate(null);
        result = tradeMaturityDateValidator.validate(td);
        Assert.assertFalse( result);
    }
}
