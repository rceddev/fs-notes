package com.rree.fsnotes.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rree.fsnotes.persistance.entity.Note;


public interface NoteRepository extends JpaRepository<Note, Integer>{

}
