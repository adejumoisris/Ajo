package com.AjoPay.AjoPay.exceptions;

import com.AjoPay.AjoPay.model.ErroMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
// this class send the Exception response to client
@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

        // method that send Error message to client
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity <ErroMessage> userNotFoundException(UserNotFoundException exception, WebRequest request){
        ErroMessage message = new ErroMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
}
