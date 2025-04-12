package com.example.services;

import com.example.dto.EstadoDto;
import com.example.entities.Estado;
import com.example.mapper.EntityDtoMapper;
import com.example.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private EntityDtoMapper entityDtoMapper;

    public List<EstadoDto> findAll(){
        return entityDtoMapper.entityToDtoList(estadoRepository.findAll(), EstadoDto.class);
    }

}
