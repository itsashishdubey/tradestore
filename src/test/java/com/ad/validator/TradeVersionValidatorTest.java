package com.ad.validator;

import com.ad.model.TradeDetails;
import com.ad.model.TradeDetailsDataTest;
import org.junit.Assert;
import org.junit.Test;

public class TradeVersionValidatorTest {
    TradeVersionValidator tradeVersionValidator = new TradeVersionValidator();

    @Test
    public void testValidate() {
        TradeDetails newTrade = TradeDetailsDataTest.getValidTrades().get(0);
        TradeDetails existingTrade = TradeDetailsDataTest.getValidTrades().get(0);

        existingTrade.setVersion(newTrade.getVersion() - 1);
        boolean result = tradeVersionValidator.validate(newTrade, existingTrade);
        Assert.assertTrue(result);

        existingTrade.setVersion(newTrade.getVersion() + 1);
        result = tradeVersionValidator.validate(newTrade, existingTrade);
        Assert.assertFalse(result);
    }
}
