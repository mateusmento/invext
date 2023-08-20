package com.invext.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidServiceRequestFinishingException extends ResponseStatusException {
    public InvalidServiceRequestFinishingException(String reason) {
        super(HttpStatus.BAD_REQUEST, reason);
    }
}
