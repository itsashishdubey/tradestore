package com.ad.helper;

/**
 * Maturity date exception class
 */
public class MaturityDateException extends RuntimeException{

    private static final long serialVersionUID = -900897745766939L;

    public MaturityDateException(String message){
        super(message);
    }
}
