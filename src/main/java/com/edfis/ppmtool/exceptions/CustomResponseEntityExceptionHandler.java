package com.edfis.ppmtool.exceptions;

import com.edfis.ppmtool.exceptions.project.ProjectNotFoundException;
import com.edfis.ppmtool.exceptions.project.ProjectNotFoundExceptionResponse;
import com.edfis.ppmtool.exceptions.projectId.ProjectIdException;
import com.edfis.ppmtool.exceptions.projectId.ProjectIdExceptionResponse;
import com.edfis.ppmtool.exceptions.registration.UsernameAlreadyExistsException;
import com.edfis.ppmtool.exceptions.registration.UsernameAlreadyExistsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleProjectIdException(ProjectIdException exception, WebRequest request){
        ProjectIdExceptionResponse exceptionResponse = new ProjectIdExceptionResponse(exception.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleProjectNotFoundException(ProjectNotFoundException exception, WebRequest request){
        ProjectNotFoundExceptionResponse exceptionResponse = new ProjectNotFoundExceptionResponse(exception.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleUsernameAlreadyExists(UsernameAlreadyExistsException exception, WebRequest request){
        UsernameAlreadyExistsResponse usernameAlreadyExistsResponse = new UsernameAlreadyExistsResponse(exception.getMessage());
        return new ResponseEntity<>(usernameAlreadyExistsResponse, HttpStatus.BAD_REQUEST);
    }
}
