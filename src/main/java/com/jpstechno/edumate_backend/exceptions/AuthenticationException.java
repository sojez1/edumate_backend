package com.jpstechno.edumate_backend.exceptions;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException(String exc) {
        super(exc);
    }

}
