package com.example.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest {
    private String email;
    private String username;
    private String password;
}
