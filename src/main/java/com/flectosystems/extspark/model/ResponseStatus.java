package com.flectosystems.extspark.model;

/**
 * Created by Ernesto Mancebo T on 3/4/15.
 */
public class ResponseStatus {

    private String message;
    private boolean success;

    public ResponseStatus(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
