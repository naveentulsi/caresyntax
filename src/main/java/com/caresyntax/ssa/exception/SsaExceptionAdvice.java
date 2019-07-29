package com.caresyntax.ssa.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "com.caresyntax")
@Log4j2
public class SsaExceptionAdvice {

    /**
     * Catches all exception arising out of app, except those listed below.
     *
     * @param ex - Exception instance
     * @return Safe response - saying server is down.
     */
    @ExceptionHandler({Throwable.class, Exception.class})
    public ResponseEntity HandleAll(Exception ex) {
        log.info("Exception Caught");
        return (ex == null) ? error() : error(ex, ex.getMessage());
    }

    /**
     * To handle all invalid data exception out of app, reason and message is contained in exception instance. Same data is transported to API user as response body.
     *
     * @param ex - SsaInvalidDataException instance
     * @return Safe response - stating actual reason.
     */
    @ExceptionHandler(SsaInvalidDataException.class)
    public ResponseEntity HandleInvalidDataException(SsaInvalidDataException ex) {
        log.info("Exception Caught");
        return (ex == null) ? error() : error(ex, ex.getMessage());
    }

    /**
     * Creates the generic response object for handled exception calls, if the exception object is null
     *
     * @return
     */
    private ResponseEntity error() {
        return ResponseEntity.ok(new SsaExceptionResponse("Server Exception", "Server is down, try again in some time."));
    }

    /**
     * Method logs the exception and creates a generic response object with the reason & message to be sent to API user.
     *
     * @param ex
     * @param message
     * @return
     */
    private ResponseEntity error(Exception ex, String message) {
        String reason = ex.getClass().getName();
        logError(ex);
        if (ex instanceof SsaInvalidDataException) {
            SsaInvalidDataException ssaInvalidDataException = (SsaInvalidDataException) ex;
            reason = ssaInvalidDataException.getReason();
            message = ssaInvalidDataException.getMessage();
        }

        return ResponseEntity.ok(new SsaExceptionResponse(reason, message));
    }

    /**
     * Plain exception logger for this class.
     *
     * @param ex exception instance
     */
    private void logError(Exception ex) {
        log.error(ex.getMessage(), ex);
    }

}
