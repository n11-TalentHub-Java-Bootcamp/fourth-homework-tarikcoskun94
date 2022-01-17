package com.n11.fourthhomeworktarikcoskun94.exception;

import com.n11.fourthhomeworktarikcoskun94.dto.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * START: HttpStatus.NOT_FOUND Exceptions
     */
    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException userNotFoundException) {

        ExceptionDTO exceptionDTO = ExceptionDTO.builder()
                .errorTitle(userNotFoundException.getErrorTitle())
                .errorMessages(userNotFoundException.getErrorMessages())
                .httpStatus(HttpStatus.NOT_FOUND)
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {BasicMessageResponseException.class})
    public ResponseEntity<?> handleRequestException(BasicMessageResponseException basicMessageResponseException) {

        String responseMessage = basicMessageResponseException.getMessage();

        return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
    }
    /** END: HttpStatus.NOT_FOUND Exceptions */

    /**
     * START: HttpStatus.BAD_REQUEST Exceptions
     */
    @ExceptionHandler(value = {UserExistingFieldException.class})
    public ResponseEntity<?> handleUserExistingFieldException(UserExistingFieldException userExistingFieldException) {

        ExceptionDTO exceptionDTO = ExceptionDTO.builder()
                .errorTitle(userExistingFieldException.getErrorTitle())
                .errorMessages(userExistingFieldException.getErrorMessages())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDTO, HttpStatus.BAD_REQUEST);
    }
    /** END: HttpStatus.BAD_REQUEST Exceptions */
}
