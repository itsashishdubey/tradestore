package com.ad.helper;

/**
 * Maturity date exception class
 */
public class VersionException extends RuntimeException{

    private static final long serialVersionUID = -900897745766939L;

    public VersionException(String message){
        super(message);
    }
}
