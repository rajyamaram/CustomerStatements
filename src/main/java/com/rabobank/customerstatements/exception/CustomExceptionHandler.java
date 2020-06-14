package com.rabobank.customerstatements.exception;

import com.rabobank.customerstatements.domain.ErrorRecord;
import com.rabobank.customerstatements.domain.ProcessorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(value={HttpMessageNotReadableException.class})
    public ResponseEntity<ProcessorResponse> somethingWentWrong(Exception e){
        ProcessorResponse pR=new ProcessorResponse("BAD_REQUEST",new ArrayList<ErrorRecord>());
        return new ResponseEntity<ProcessorResponse>(pR, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value={NullPointerException.class})
    public ResponseEntity<ProcessorResponse> someFieldInTheInputJsonIsMissing(Exception e){
        ProcessorResponse pR=new ProcessorResponse("INTERNAL_SERVER_ERROR",new ArrayList<ErrorRecord>());
        return new ResponseEntity<ProcessorResponse>(pR, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
