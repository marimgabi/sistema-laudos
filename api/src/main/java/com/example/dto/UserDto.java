package com.example.dto;

import com.example.dto.login.MedicoLoginDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDto {

    private Integer id;

    private String email;

    private String username;

    private RoleDto role;

    private List<MedicoDto> medicos = new ArrayList<>();
}
