package com.korotin.quizlet.exception;

import java.util.UUID;

public class SetNotFoundException extends RuntimeException {
    public SetNotFoundException(UUID id) {
        super("Set with id '%s' doesn't exists.".formatted(id.toString()));
    }
}
