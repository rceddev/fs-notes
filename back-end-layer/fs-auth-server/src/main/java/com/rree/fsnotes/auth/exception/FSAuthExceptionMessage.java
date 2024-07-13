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

    public static final String USER_ALREADY_EXIST_MESSAGE = "User already exists";
    public static final String USER_ALREADY_EXIST_CODE = "409_UE";

    public static final String SERVER_ERROR = "5XX_ERROR";
    public static final String BAD_REQUEST = "4XX_BR";

    private String code;
    private String message;
}
