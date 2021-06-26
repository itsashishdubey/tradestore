package com.ad.validator;

@FunctionalInterface
public interface VersionValidator<T> {
    boolean validate(T newObject, T existingObject);
}
