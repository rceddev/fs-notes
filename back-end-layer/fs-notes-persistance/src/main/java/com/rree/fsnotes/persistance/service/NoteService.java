package com.rree.fsnotes.persistance.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rree.fsnotes.persistance.entity.Note;
import com.rree.fsnotes.persistance.entity.User;
import com.rree.fsnotes.persistance.exception.CustomExceptionHandler;
import com.rree.fsnotes.persistance.repository.NoteRepository;

@Service
public class NoteService {
	
	@Autowired
	private NoteRepository noteRepository;
	@Autowired
	private UserService userService;
	
	public List<Note> getAllNotes(Integer userId){
		User user = userService.getUserById(userId);
		return user.getNotes();
	}
	
	public Note saveNote(Integer userId, Note note) {
		User user = userService.getUserById(userId);
		
		note.setUser(user);
		note.setCreated(new Timestamp(System.currentTimeMillis()));
		note.setUpdated(new Timestamp(System.currentTimeMillis()));
		
		Note noteSaved = noteRepository.save(note);
		return noteSaved;
	}

	public Note getNote(Integer idUser, Integer idNote) {
		
		User user = userService.getUserById(idUser);
		
		Note note = user.getNotes()
						.stream()
						.filter( id -> id.getIdNote().equals(idNote))
						.findFirst()
						.orElse(null);
		
		if(note == null)
			throw new CustomExceptionHandler.NoteNotFoundException("Note:" + idNote + " not found");
		return note;
	}

	public Note updateNote(Integer idUser, Integer idNote, Note note) {
		//TODO: Use exits validation instead of saving object in memory
		User user = userService.getUserById(idUser);
		
		//TODO; User noteRepository to get note
		Note noteToUpdate = user.getNotes()
						.stream()
						.filter( id -> id.getIdNote().equals(idNote))
						.findFirst()
						.orElse(null);
		
		if(noteToUpdate == null)
			throw new CustomExceptionHandler.NoteNotFoundException("Note:" + idNote + " not found");
		
		noteToUpdate.setTitle(note.getTitle());
		noteToUpdate.setContent(note.getContent());
		noteToUpdate.setUpdated(new Timestamp(System.currentTimeMillis()));
		
		return noteRepository.save(noteToUpdate);
	}
}
