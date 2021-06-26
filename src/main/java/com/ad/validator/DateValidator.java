package com.ad.validator;

@FunctionalInterface
public interface DateValidator<T> {
    boolean validate(T obj);
}
