package com.cybersoft.cozastore.exception;

import com.cybersoft.cozastore.payload.BaseResponse;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = {AuthenticationCredentialsNotFoundException.class, AccessDeniedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<?> unauthorize(HttpServletRequest req, Exception e) throws Exception {
        BaseResponse response = new BaseResponse();
        response.setStatusCode(401);
        response.setMessage(e.getLocalizedMessage());
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.valueOf(401));
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        BaseResponse response = new BaseResponse();
        response.setStatusCode(500);
        response.setMessage(e.getLocalizedMessage());
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.valueOf(500));
    }


}