package com.ad.handler;

@FunctionalInterface
public interface Processor<T> {
    void process(T obj);
}
