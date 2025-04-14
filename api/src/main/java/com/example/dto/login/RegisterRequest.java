package com.example.dto.login;

import com.example.dto.MedicoDto;
import com.example.dto.RoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest {

    private String email;

    private String username;

    private String password;

    private MedicoDto medico;

    private RoleDto role;
}
