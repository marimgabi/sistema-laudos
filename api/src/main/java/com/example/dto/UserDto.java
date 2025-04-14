package com.example.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private String email;

    private String username;

    private MedicoDto medico;
}
