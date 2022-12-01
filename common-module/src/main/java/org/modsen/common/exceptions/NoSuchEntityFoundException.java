package org.modsen.common.exceptions;

public class NoSuchEntityFoundException extends RuntimeException {
    public NoSuchEntityFoundException(String message) {
        super(message);
    }
}
