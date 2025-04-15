package com.example.services;

import com.example.Enums.EnumStatus;
import com.example.dto.MedicoDto;
import com.example.dto.UserDto;
import com.example.dto.UserResumeDto;
import com.example.dto.login.RegisterRequest;
import com.example.dto.login.UserLoginDto;
import com.example.entities.Role;
import com.example.entities.User;
import com.example.mapper.EntityDtoMapper;
import com.example.repositories.UserRepository;
import com.example.utils.PartialMergeUtil;
import com.example.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
            List<MedicoDto> medicoDtoList = new ArrayList<>();
            medicoDtoList.add(medicoDto);
            userCreated.setMedicos(medicoDtoList);
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

    public List<UserResumeDto> findAll(){
        List<User> users = userRepository.findAllByStatus(EnumStatus.ATIVO);
        return mapper.entityToDtoList(users, UserResumeDto.class);
    }

    @Transactional
    public UserResumeDto inativarUsuario(Integer id){
        User user = userRepository.findByIdAndStatus(id, EnumStatus.ATIVO)
                .orElseThrow(() -> new IllegalArgumentException("Não foi encontrado usuário ativo com id " + id));

        user.setStatus(EnumStatus.INATIVO);

        if(user.getMedicos() != null){
            user.getMedicos().forEach(m -> medicoService.inativate(m));
        }

        user = userRepository.save(user);

        return (UserResumeDto) mapper.entityToDto(user, UserResumeDto.class);
    }
}
