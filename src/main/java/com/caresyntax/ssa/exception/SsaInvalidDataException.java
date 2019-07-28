package com.caresyntax.ssa.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class SsaInvalidDataException extends RuntimeException {

    private String message;
    private String reason;
}
