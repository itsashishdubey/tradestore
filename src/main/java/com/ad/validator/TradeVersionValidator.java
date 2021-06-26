package com.ad.validator;

import com.ad.model.TradeDetails;
import org.springframework.stereotype.Component;

@Component
public class TradeVersionValidator implements VersionValidator<TradeDetails> {

    /**
     * Compares the version of newTrade and existingTrade and return true if NewVersion is greater
     * than equals to ExistingVersion
     * @param newTrade
     * @param existingTrade
     * @return
     */
    @Override
    public boolean validate(TradeDetails newTrade, TradeDetails existingTrade) {
        return newTrade.getVersion() >= existingTrade.getVersion();
    }
}
