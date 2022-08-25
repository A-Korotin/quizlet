package com.korotin.quizlet.exception;

public class EndpointException extends CustomException {


    public EndpointException(String msg) {
        super(msg);
        TYPE = "This page could not be found.";
    }
}
