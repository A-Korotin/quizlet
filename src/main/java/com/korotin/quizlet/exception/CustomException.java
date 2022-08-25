package com.korotin.quizlet.exception;

public class CustomException extends RuntimeException {

    protected static String TYPE = "UNDEFINED";

    public CustomException(String msg) {
        super(msg);
    }

    public String getType() {
        return TYPE;
    }
}
