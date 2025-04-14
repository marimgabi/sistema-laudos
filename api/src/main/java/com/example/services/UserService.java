package com.example.services;

import com.example.Enums.EnumStatus;
import com.example.dto.MedicoDto;
import com.example.dto.UserDto;
import com.example.dto.login.RegisterRequest;
import com.example.dto.login.UserLoginDto;
import com.example.entities.Role;
import com.example.entities.User;
import com.example.mapper.EntityDtoMapper;
import com.example.repositories.UserRepository;
import com.example.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EntityDtoMapper mapper;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private RoleService roleService;

    @Transactional
    public UserDto create(RegisterRequest userDto) {
        User user = (User) mapper.dtoToEntity(userDto, User.class);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(EnumStatus.ATIVO);
        Role role = roleService.findById(user.getRole().getId());
        user.setRole(role);

        userValidator.validadeInsert(user);

        user = userRepository.save(user);

        UserDto userCreated = (UserDto) mapper.entityToDto(user, UserDto.class);

        if(userDto.getMedico() != null){
            MedicoDto medicoDto = medicoService.create(userDto.getMedico(), user);
            userCreated.getMedicos().add(medicoDto);
        }

        return userCreated;
    }

    public User getUsuarioLogado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        return userRepository.findByEmailAndStatus(email, EnumStatus.ATIVO)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário logado não encontrado"));
    }

    public UserLoginDto getUsuariologado(String email){
        User user = userRepository.findByEmailAndStatus(email, EnumStatus.ATIVO)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário com email " + email + " não encontrado!"));
        return (UserLoginDto) mapper.entityToDto(user, UserLoginDto.class);
    }
}
