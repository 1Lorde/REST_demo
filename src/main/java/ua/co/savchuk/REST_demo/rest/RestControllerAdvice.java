package ua.co.savchuk.REST_demo.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
@RequestMapping(produces = "application/json")
@ResponseBody
public class RestControllerAdvice {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> responseStatus(final ResponseStatusException e) {
        Map<String, Object> errorInfo = new LinkedHashMap<>();
        errorInfo.put("timestamp", new Date());
        errorInfo.put("httpCode", e.getStatus().value());
        errorInfo.put("httpStatus", e.getStatus().getReasonPhrase());
        errorInfo.put("errorReason", e.getReason());
        return new ResponseEntity<Map<String, Object>>(errorInfo, e.getStatus());
    }

}
