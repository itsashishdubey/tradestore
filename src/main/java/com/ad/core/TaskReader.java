package com.ad.core;


@FunctionalInterface
public interface TaskReader<T> {
    /**
     * Get Task which was added by Task Writer
     * @return
     * @throws InterruptedException
     */
    T getTask() throws InterruptedException;
}
