package com.ad.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class TradeDetailsDataTest {
    TradeDetails tradeDetails = new TradeDetails();


    public static List<TradeDetails> getAllTradeDetails() {
        List<TradeDetails> trades = getValidTrades();
        trades.addAll(getInvalidTrades());

        return trades;
    }

    public static List<TradeDetails> getValidTrades(){
        List<TradeDetails> tradesList = new LinkedList<>();

        tradesList.add(createTradeDetails(10L, 1L, LocalDate.now().plusDays(10)));
        tradesList.add(createTradeDetails(10L, 3L, LocalDate.now().plusDays(10)));
        tradesList.add(createTradeDetails(10L, 2L, LocalDate.now().plusDays(10)));


        tradesList.add(createTradeDetails(20L, 1L, LocalDate.now().plusDays(10)));
        return tradesList;
    }

    public static List<TradeDetails> getInvalidTrades(){
        List<TradeDetails> tradesList = new LinkedList<>();

        tradesList.add(createTradeDetails(10L, 1L, LocalDate.now().minusDays(10)));

        return tradesList;
    }

    public static TradeDetails createTradeDetails(Long tradeId, Long tradeVersion, LocalDate maturityDate){
        TradeDetails tradeDetails = new TradeDetails();
        tradeDetails.setTradeId(tradeId);
        tradeDetails.setVersion(tradeVersion);

        tradeDetails.setMaturityDate(maturityDate);

        tradeDetails.setExpired(false);
        tradeDetails.setCreatedDate(LocalDate.now());
        tradeDetails.setBookId("BookId");
        tradeDetails.setCounterpartyId("CounterpartyId");
        return tradeDetails;
    }

}