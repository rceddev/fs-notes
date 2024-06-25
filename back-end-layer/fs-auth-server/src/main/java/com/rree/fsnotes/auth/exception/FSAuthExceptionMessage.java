package com.rree.fsnotes.auth.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class FSAuthExceptionMessage {

    public static final String WRONG_PASS_MESSAGE = "Wrong password";
    public static final String WRONG_PASS_CODE = "401_WP";
    public static final String EMAIL_NOT_FOUND_MESSAGE = "Email not found";
    public static final String EMAIL_NOT_FOUND_CODE = "401_ENF";

    private String code;
    private String message;
}
