package com.n11.fourthhomeworktarikcoskun94.exception;

import java.util.List;

public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException() {
    }

    public UserNotFoundException(String... errorMessages) {
        super(errorMessages);
    }

    public UserNotFoundException(List<String> errorMessages) {
        super(errorMessages);
    }
}
