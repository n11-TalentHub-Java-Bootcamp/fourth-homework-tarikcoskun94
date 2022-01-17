package com.n11.fourthhomeworktarikcoskun94.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;
import java.util.List;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntityExistingFieldException extends RuntimeException {

    private String errorTitle = "Same field is existing!";
    private List<String> errorMessages;

    public EntityExistingFieldException() {
    }

    public EntityExistingFieldException(String... errorMessages) {
        this.errorMessages = Arrays.asList(errorMessages);
    }

    public EntityExistingFieldException(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    protected String getErrorTitle() {
        return errorTitle;
    }

    protected List<String> getErrorMessages() {
        return errorMessages;
    }
}
