package org.personservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnknownPersonTypeException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public UnknownPersonTypeException(String message) {
        super(message);
    }
}
