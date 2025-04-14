package com.example.services;

import com.example.dto.RoleDto;
import com.example.entities.Role;
import com.example.mapper.EntityDtoMapper;
import com.example.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EntityDtoMapper mapper;

    public List<RoleDto> findAll(){
        List<Role> roles = roleRepository.findAll();
        return mapper.entityToDtoList(roles, RoleDto.class);
    }

    public Role findById(Integer id){
        Optional<Role> role = roleRepository.findById(id);
        if(role.isPresent())
            return role.get();
        throw new IllegalArgumentException("Não existe role com id: " + id + "!");
    }

}
