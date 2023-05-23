package com.rree.fsnotes.persistance.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FSNotesExceptionMessage {
	private LocalDateTime localDateTime;
	private String message;
	private String details;
}
