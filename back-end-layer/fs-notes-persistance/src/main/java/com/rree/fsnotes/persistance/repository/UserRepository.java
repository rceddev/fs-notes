package com.rree.fsnotes.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rree.fsnotes.persistance.entity.User;

public interface UserRepository  extends JpaRepository<User, Integer>{

}
