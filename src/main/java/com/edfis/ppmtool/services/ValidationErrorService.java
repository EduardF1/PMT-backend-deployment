package com.edfis.ppmtool.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class ValidationErrorService {

    public ResponseEntity<?> validate(BindingResult result) {
        return result.hasErrors() ?
                new ResponseEntity<>(new HashMap<>(
                        result
                        .getFieldErrors()
                        .stream()
                        .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage))), HttpStatus.BAD_REQUEST
                )
                : null;
    }
}
