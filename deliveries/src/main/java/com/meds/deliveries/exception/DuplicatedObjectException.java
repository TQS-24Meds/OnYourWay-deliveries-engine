package com.meds.deliveries.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicatedObjectException extends Exception {

    private static final long serialVersionUID = 1L;

    public DuplicatedObjectException(String message){ super(message); }
}
