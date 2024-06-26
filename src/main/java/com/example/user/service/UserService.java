package com.example.user.service;

import com.example.user.dto.RoleDTO;
import com.example.user.dto.UserDTO;
import com.example.user.entity.User;
import com.example.user.exception.*;
import com.example.user.mapper.UserMapper;
import com.example.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO register(UserDTO userDTO) throws UserRegistrationException {
        User user = userMapper.toEntity(userDTO);
        userRepository.save(user);
        return userMapper.toDTO(user);
    }

    @Override
    public boolean authenticate(String username, String password) throws AuthenticationException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found"));
        return user.authenticate(password);
    }

    @Override
    public void addRole(int userId, RoleDTO roleDTO) throws UserNotFoundException, RoleAssignmentException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        user.addRole(userMapper.toEntity(roleDTO));
        userRepository.save(user);
    }

    @Override
    public void removeRole(int userId, RoleDTO roleDTO) throws UserNotFoundException, RoleRemovalException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        user.removeRole(userMapper.toEntity(roleDTO));
        userRepository.save(user);
    }
}
