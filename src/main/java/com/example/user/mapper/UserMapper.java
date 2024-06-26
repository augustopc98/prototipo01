package com.example.user.mapper;

import com.example.user.dto.RoleDTO;
import com.example.user.dto.UserDTO;
import com.example.user.entity.Role;
import com.example.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDTO userDTO);
    UserDTO toDTO(User user);
    Role toEntity(RoleDTO roleDTO);
    RoleDTO toDTO(Role role);
}
