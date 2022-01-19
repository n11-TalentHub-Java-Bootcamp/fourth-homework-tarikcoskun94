package com.n11.fourthhomeworktarikcoskun94.exception;

import java.util.List;

public class LoanNotFoundException extends EntityNotFoundException {

    public LoanNotFoundException() {
    }

    public LoanNotFoundException(String... errorMessages) {
        super(errorMessages);
    }

    public LoanNotFoundException(List<String> errorMessages) {
        super(errorMessages);
    }
}
