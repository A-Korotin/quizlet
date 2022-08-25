package com.korotin.quizlet.controller.advice;

import com.korotin.quizlet.annotation.HandleUrlExceptions;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice(annotations = HandleUrlExceptions.class)
public class UrlExceptionResolver {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleArgumentMismatchException(MethodArgumentTypeMismatchException e, Model model) {

        model.addAttribute("cause", "This page could not be loaded. Please check your URL.");
        model.addAttribute("info", e.getMessage());

        return "error";
    }
}
