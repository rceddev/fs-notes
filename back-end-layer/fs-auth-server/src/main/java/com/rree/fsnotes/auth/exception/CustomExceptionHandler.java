package com.rree.fsnotes.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(WrongPasswordException.class)
    public final ResponseEntity<FSAuthExceptionMessage> handleWrongPassWordException(Exception ex, WebRequest request) throws Exception {
        FSAuthExceptionMessage errorException = new FSAuthExceptionMessage(
                FSAuthExceptionMessage.WRONG_PASS_CODE, FSAuthExceptionMessage.WRONG_PASS_MESSAGE);
        return new ResponseEntity<FSAuthExceptionMessage>(errorException, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public final ResponseEntity<FSAuthExceptionMessage> handleEmailNotFoundException(Exception ex, WebRequest request) throws Exception {
        FSAuthExceptionMessage errorException = new FSAuthExceptionMessage(
                FSAuthExceptionMessage.EMAIL_NOT_FOUND_CODE, FSAuthExceptionMessage.EMAIL_NOT_FOUND_MESSAGE);
        return new ResponseEntity<FSAuthExceptionMessage>(errorException, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ServerErrorException.class)
    public final ResponseEntity<FSAuthExceptionMessage> handleServerErrorException(Exception ex, WebRequest request) throws  Exception {
        FSAuthExceptionMessage errorException = new FSAuthExceptionMessage(
                FSAuthExceptionMessage.SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<FSAuthExceptionMessage>(errorException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public final ResponseEntity<FSAuthExceptionMessage> handleUserAlreadyExistException(Exception ex, WebRequest request) throws  Exception {
        FSAuthExceptionMessage errorException = new FSAuthExceptionMessage(
                FSAuthExceptionMessage.USER_ALREADY_EXIST_CODE, FSAuthExceptionMessage.USER_ALREADY_EXIST_MESSAGE);
        return new ResponseEntity<FSAuthExceptionMessage>(errorException, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(FSBadRequestException.class)
    public final ResponseEntity<FSAuthExceptionMessage> badRequestException(Exception ex, WebRequest request) throws  Exception {
        FSAuthExceptionMessage errorException = new FSAuthExceptionMessage(
                FSAuthExceptionMessage.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<FSAuthExceptionMessage>(errorException, HttpStatus.BAD_REQUEST);
    }

    public static class EmailNotFoundException extends RuntimeException{
        private static final long serialVersionUID = 1L;

        public EmailNotFoundException(String message){super(message);}
    }
    public static class WrongPasswordException extends RuntimeException{
        private static final long serialVersionUID = 1L;

        public WrongPasswordException(String message){super(message);}
    }

    public static class ServerErrorException extends RuntimeException{
        private static final long serialVersionUID = 1L;

        public ServerErrorException(String message){super(message);}
    }

    public static class UserAlreadyExistException extends RuntimeException{
        private static final long serialVersionUID = 1L;

        public UserAlreadyExistException(String message){super(message);}
    }

    public static class FSBadRequestException extends RuntimeException{
        private static final long serialVersionUID = 1L;
        public FSBadRequestException(String message){super(message);}
    }
}
