package com.ad.handler;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class LockManager implements ILockManager{
    private final Map<Long, ReentrantLock> lockMap = new ConcurrentHashMap<>();

    /**
     * Used lock stripping concept, creating unique lock per trade id.
     * if lock exists for a tradeId, then returns that lock otherwise creates new lock
     * @param tradeId
     * @return
     */
    public ReentrantLock getLockByTradeId(Long tradeId){
        if(lockMap.get(tradeId) != null)
            return lockMap.get(tradeId);
        lockMap.putIfAbsent(tradeId, new ReentrantLock());
        return lockMap.get(tradeId);
    }
}
