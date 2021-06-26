package com.ad.handler;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Manages lock
 */
@FunctionalInterface
public interface ILockManager {
    /**
     * Return the lock based on the identifier
     * @param identifier
     * @return
     */
    ReentrantLock getLockByTradeId(Long identifier);
}
