package com.example.dto;

import com.example.dto.login.MedicoLoginDto;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private String email;

    private String username;

    private List<MedicoDto> medicos;
}
