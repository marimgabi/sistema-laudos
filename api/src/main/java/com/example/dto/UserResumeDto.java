package com.example.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserResumeDto {

    private Integer id;

    private String email;

    private String username;

    private RoleDto role;
}
