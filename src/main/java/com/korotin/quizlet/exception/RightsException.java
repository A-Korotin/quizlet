package com.korotin.quizlet.exception;

public class RightsException extends CustomException {
    public RightsException(String msg) {
        super(msg);
        TYPE = "You do not have rights to access this page";
    }
}
