package com.ad.service;

/**
 * Entry point, receiver interface accept the object
 *
 * @param <T>
 */
@FunctionalInterface
public interface Receiver<T> {
    void accept(T object);
}
