package com.cybersoft.cozastore.exception;

import com.cybersoft.cozastore.payload.BaseResponse;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        if (AnnotationUtils.findAnnotation
                (e.getClass(), ResponseStatus.class) != null)
            throw e;
        BaseResponse response = new BaseResponse();
        response.setStatusCode(500);
        response.setMessage(e.getLocalizedMessage());
        response.setData(null);
       return ResponseEntity.ok(response);
    }
}