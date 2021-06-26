package com.ad.store;

import com.ad.model.TradeDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TradeStore which manages read and write to trade store Map. later it can be replaced to entity
 * class to persist or can be used to write onto distributed cache
 */
@Service
public class TradeStore {

    private final Map<Long, TradeDetails> tradeIdTradeDetailsMap = new ConcurrentHashMap<>();

    public void addTrade(TradeDetails tradeDetails){
        tradeIdTradeDetailsMap.put(tradeDetails.getTradeId(), tradeDetails);
    }

    public TradeDetails getTrade(Long tradeId){
        return tradeIdTradeDetailsMap.get(tradeId);
    }

    public Collection<TradeDetails> getAllTrades(){
        return tradeIdTradeDetailsMap.values();
    }
}
