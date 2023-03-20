package br.com.victor.restwithspringbootandjava.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = org.springframework.http.HttpStatus.BAD_REQUEST)
public class UnsupportedMathOperationException extends RuntimeException {

    public UnsupportedMathOperationException(String string) {
        super(string);
    
    }

    private static final long serialVersionUID = 1L;

}
