package com.rree.fsnotes.persistance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rree.fsnotes.persistance.entity.Note;
import com.rree.fsnotes.persistance.service.NoteService;

import jakarta.validation.Valid;

@RestController
public class NoteController {
	
	@Autowired
	private NoteService noteService;
	
	@GetMapping("/user/{idUser}/notes")
	public List<Note> getAllNotes(@PathVariable Integer idUser){
		return noteService.getAllNotes(idUser);
	}

	@GetMapping("/user/{id}/notes/{idNote}")
	public Note getNote(@PathVariable Integer id, @PathVariable Integer idNote){
		return noteService.getNote(id, idNote);
	}
	
	@PostMapping("/user/{id}/note")
	public Note saveNote(@PathVariable Integer id,@Valid @RequestBody Note note){
		return noteService.saveNote(id, note);
	}
	
	@PutMapping("/user/{idUser}/notes/{idNote}")
	public Note updateNote(@PathVariable Integer idUser, @PathVariable Integer idNote, @Valid @RequestBody Note note) {
		return noteService.updateNote(idUser, idNote, note);
	}
}
