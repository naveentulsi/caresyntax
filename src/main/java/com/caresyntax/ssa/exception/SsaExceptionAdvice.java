package com.caresyntax.ssa.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "com.caresyntax")
@Log4j2
public class SsaExceptionAdvice {

    @ExceptionHandler({Throwable.class, Exception.class})
    public ResponseEntity HandleAll(Exception ex){
        log.info("Exception Caught");
        return (ex == null) ?  error() : error(ex, ex.getMessage());
    }

    @ExceptionHandler(SsaInvalidDataException.class)
    public ResponseEntity HandleInvalidDataException(SsaInvalidDataException ex){
        log.info("Exception Caught");
        return (ex == null) ?  error() : error(ex, ex.getMessage());
    }

    private ResponseEntity error(){
        return ResponseEntity.ok(new SsaExceptionResponse("Server Exception", "Server is down, try again in some time."));
    }

    private ResponseEntity error(Exception ex, String message){
        String reason = ex.getClass().getName();
        logError(ex);
        if(ex instanceof SsaInvalidDataException){
            SsaInvalidDataException ssaInvalidDataException = (SsaInvalidDataException) ex;
            reason = ssaInvalidDataException.getReason();
            message = ssaInvalidDataException.getMessage();
        }

        return ResponseEntity.ok(new SsaExceptionResponse(reason, message));
    }

    private void logError(Exception ex){
        log.error(ex.getMessage(), ex);
    }

}
