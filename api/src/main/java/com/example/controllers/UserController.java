package com.example.controllers;

import com.example.dto.UserDto;
import com.example.dto.UserResumeDto;
import com.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResumeDto>> getAllUsers(){
        List<UserResumeDto> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}/inativate")
    public ResponseEntity<UserResumeDto> inativateUser(@PathVariable Integer id){
        UserResumeDto userDto = userService.inativarUsuario(id);
        return ResponseEntity.ok(userDto);
    }
}
