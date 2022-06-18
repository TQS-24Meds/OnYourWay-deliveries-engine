package com.meds.deliveries.exception;

import lombok.Getter;

import java.util.Date;


@Getter
public class ErrorDetails {

    private Date timestamp;
    private String message;
    private String details;

}
