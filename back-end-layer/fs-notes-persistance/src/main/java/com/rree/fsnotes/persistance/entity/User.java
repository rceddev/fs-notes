package com.rree.fsnotes.persistance.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity(name = "users")
public class User {
	
	public User() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Size(min = 2, message = "Name must have atleast 2 characters")
	@NotEmpty(message = "Name should not be empty")
	private String firstName;
	@Size(min = 2, message = "Last name must have atleast 2 characters")
	@NotEmpty(message = "Last name should not be empty")
	private String lastName;
	
	@Column(unique = true)
	private String email;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Note> notes;
}
