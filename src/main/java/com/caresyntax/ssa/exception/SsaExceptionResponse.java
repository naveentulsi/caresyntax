package com.caresyntax.ssa.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SsaExceptionResponse {

    private String reason;
    private String message;
}
