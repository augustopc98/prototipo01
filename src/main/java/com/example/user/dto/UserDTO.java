package com.example.user.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private int id;
    private String username;
    private String email;
    private List<RoleDTO> roles;
}
