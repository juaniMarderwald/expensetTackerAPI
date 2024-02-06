package com.juani.expensetrackerapi.exception;

import com.juani.expensetrackerapi.entity.ErrorObject;
import com.juani.expensetrackerapi.utils.Utils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorObject> handleExpenseNotFoundException(ResourceNotFoundException ex, WebRequest request){
        return new ResponseEntity<ErrorObject>(Utils.generateErrorObject(ex.getMessage(),HttpStatus.NOT_FOUND.value()), HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorObject> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request){
        return new ResponseEntity<ErrorObject>(Utils.generateErrorObject(ex.getMessage(),HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObject> handleGeneralException(Exception ex, WebRequest request){
        return new ResponseEntity<ErrorObject>(Utils.generateErrorObject(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,Object> body = new HashMap<>();
        body.put("timestamp", new Date());
        body.put("statusCode", HttpStatus.BAD_REQUEST.value());
        List<String> errors = ex.getBindingResult().getFieldErrors()
                             .stream()
                             .map(x->x.getDefaultMessage()).collect(Collectors.toList());

        body.put("messages", errors);

        return new ResponseEntity<>(body, HttpStatus.OK);

    }

    @ExceptionHandler(ItemAlreadyExistException.class)
    public ResponseEntity<ErrorObject> HandleItemAlreadyExistException(Exception ex, WebRequest request){

        return new ResponseEntity<ErrorObject>(Utils.generateErrorObject(ex.getMessage(),HttpStatus.CONFLICT.value()), HttpStatus.OK);

    }

}
