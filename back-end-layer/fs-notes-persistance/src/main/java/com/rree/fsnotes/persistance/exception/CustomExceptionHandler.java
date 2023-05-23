package com.rree.fsnotes.persistance.exception;


import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{
	
	
	
	@ExceptionHandler(CustomExceptionHandler.UserNotFoundException.class)
	public final ResponseEntity<FSNotesExceptionMessage> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {
		FSNotesExceptionMessage errorException = new FSNotesExceptionMessage(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<FSNotesExceptionMessage>(errorException, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CustomExceptionHandler.NoteNotFoundException.class)
	public final ResponseEntity<FSNotesExceptionMessage> handleNoteNotFoundException(Exception ex, WebRequest request) throws Exception {
		FSNotesExceptionMessage errorException = new FSNotesExceptionMessage(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<FSNotesExceptionMessage>(errorException, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CustomExceptionHandler.UserAlreaydExistsException.class)
	public final ResponseEntity<FSNotesExceptionMessage> handleUserAlreadyException(Exception ex, WebRequest request) throws Exception {
		FSNotesExceptionMessage errorException = new FSNotesExceptionMessage(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<FSNotesExceptionMessage>(errorException, HttpStatus.CONFLICT);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
			FSNotesExceptionMessage errorException = new FSNotesExceptionMessage(LocalDateTime.now(), ex.getFieldError().getDefaultMessage(), request.getDescription(false));
			return new ResponseEntity<Object>(errorException, HttpStatus.BAD_REQUEST);
	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public static class UserNotFoundException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public UserNotFoundException(String message) {
			super(message);
		}
	}
	
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public static class UserAlreaydExistsException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public UserAlreaydExistsException(String message) {
			super(message);
		}
	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public static class NoteNotFoundException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public NoteNotFoundException(String message) {
			super(message);
		}
	}
	
}
