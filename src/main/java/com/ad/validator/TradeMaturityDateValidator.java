package com.ad.validator;

import com.ad.model.TradeDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TradeMaturityDateValidator implements DateValidator<TradeDetails> {

    private static final Logger logger = LoggerFactory.getLogger(TradeMaturityDateValidator.class);

    /**
     * Validate the trade maturity and return true if it is not passed
     * @param tradeDetails
     * @return
     */
    @Override
    public boolean validate(TradeDetails tradeDetails) {
        if(tradeDetails.getMaturityDate() == null){
            logger.error("Maturity Date is null for {}", tradeDetails);
            return false;
        }
        return LocalDate.now().minusDays(1).isBefore(tradeDetails.getMaturityDate());

    }
}
