package org.modsen.eventworker.exceptions.handler;

import org.modsen.eventworker.dao.pojo.ApplicationError;
import org.modsen.eventworker.exceptions.DuplicateEntityException;
import org.modsen.eventworker.exceptions.NoSuchEntityFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateEntityException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApplicationError catchDuplicateEntityException(DuplicateEntityException ex) {
        return new ApplicationError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(NoSuchEntityFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApplicationError catchNoSuchEntityFoundException(NoSuchEntityFoundException ex) {
        return new ApplicationError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }
}
