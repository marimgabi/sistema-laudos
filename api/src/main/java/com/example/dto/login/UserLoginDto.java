package com.example.dto.login;

import com.example.dto.MedicoDto;
import com.example.dto.RoleDto;
import lombok.Data;

import java.util.List;

@Data
public class UserLoginDto {

    private String email;

    private String username;

    private RoleDto role;

    private List<MedicoLoginDto> medicos;
}
