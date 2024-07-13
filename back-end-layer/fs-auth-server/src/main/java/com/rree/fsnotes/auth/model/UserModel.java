package com.rree.fsnotes.auth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class UserModel {
    public UserModel() {}
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
}

