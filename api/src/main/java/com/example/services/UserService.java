package com.example.services;

import com.example.Enums.EnumStatus;
import com.example.dto.UserDto;
import com.example.dto.login.RegisterRequest;
import com.example.entities.User;
import com.example.mapper.EntityDtoMapper;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EntityDtoMapper mapper;

    public UserDto createUser(RegisterRequest userDto) {
        User user = (User) mapper.dtoToEntity(userDto, User.class);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(EnumStatus.ATIVO);

        user = userRepository.save(user);

        return (UserDto) mapper.entityToDto(user, UserDto.class);
    }
}
