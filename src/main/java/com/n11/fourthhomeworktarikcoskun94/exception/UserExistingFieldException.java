package com.n11.fourthhomeworktarikcoskun94.exception;

import java.util.List;

public class UserExistingFieldException extends EntityExistingFieldException {

    public UserExistingFieldException() {
    }

    public UserExistingFieldException(String... errorMessages) {
        super(errorMessages);
    }

    public UserExistingFieldException(List<String> errorMessages) {
        super(errorMessages);
    }
}
