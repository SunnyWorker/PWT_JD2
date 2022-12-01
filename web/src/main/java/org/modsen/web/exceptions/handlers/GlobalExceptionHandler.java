package org.modsen.web.exceptions.handlers;

import org.modsen.common.dao.pojo.ApplicationError;
import org.modsen.common.exceptions.DuplicateEntityException;
import org.modsen.common.exceptions.NoSuchEntityFoundException;
import org.springframework.http.HttpStatus;
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
