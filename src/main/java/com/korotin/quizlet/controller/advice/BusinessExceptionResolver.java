package com.korotin.quizlet.controller.advice;

import com.korotin.quizlet.annotation.HandleBusinessExceptions;
import com.korotin.quizlet.exception.CustomException;
import com.korotin.quizlet.exception.EndpointException;
import com.korotin.quizlet.exception.RightsException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = HandleBusinessExceptions.class)
public class BusinessExceptionResolver {

    @ExceptionHandler({EndpointException.class, RightsException.class})
    public String handleEndpointException(CustomException e, Model model) {

        model.addAttribute("cause", "This page could not be found, or you do not have access.");
        model.addAttribute("info", e.getMessage());

        System.out.printf("Message: '%s'%n", e.getLocalizedMessage());

        return "error";
    }
}
