package com.example.user.controller;

import com.example.user.dto.RoleDTO;
import com.example.user.dto.UserDTO;
import com.example.user.exception.*;
import com.example.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public UserDTO register(@RequestBody UserDTO userDTO) throws UserRegistrationException {
        return userService.register(userDTO);
    }

    @PostMapping("/authenticate")
    public boolean authenticate(@RequestParam String username, @RequestParam String password) throws AuthenticationException {
        return userService.authenticate(username, password);
    }

    @PostMapping("/{userId}/roles")
    public void addRole(@PathVariable int userId, @RequestBody RoleDTO roleDTO) throws UserNotFoundException, RoleAssignmentException {
        userService.addRole(userId, roleDTO);
    }

    @DeleteMapping("/{userId}/roles")
    public void removeRole(@PathVariable int userId, @RequestBody RoleDTO roleDTO) throws UserNotFoundException, RoleRemovalException {
        userService.removeRole(userId, roleDTO);
    }
}
