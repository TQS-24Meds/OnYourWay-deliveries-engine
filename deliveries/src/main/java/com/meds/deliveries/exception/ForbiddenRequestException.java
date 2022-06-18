package com.meds.deliveries.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ForbiddenRequestException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ForbiddenRequestException(String message){ super(message); }
}
