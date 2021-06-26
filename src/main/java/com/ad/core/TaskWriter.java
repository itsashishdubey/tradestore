package com.ad.core;

@FunctionalInterface
public interface TaskWriter<T> {
    /**
     * Added a task
     * @param obj
     * @throws InterruptedException
     */
    public void addTask(T obj) throws InterruptedException;
}
