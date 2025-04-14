package com.example.services;

import com.example.Enums.EnumStatus;
import com.example.dto.ConselhoDto;
import com.example.entities.Conselho;
import com.example.mapper.EntityDtoMapper;
import com.example.repositories.ConselhoRepository;
import com.example.validator.ConselhoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConselhoService {

    @Autowired
    private ConselhoRepository conselhoRepository;

    @Autowired
    private EntityDtoMapper mapper;

    @Autowired
    private ConselhoValidator conselhoValidator;

    public Conselho create(Conselho conselho){
        conselho.setStatus(EnumStatus.ATIVO);

        conselhoValidator.validateInsert(conselho);

        conselho = conselhoRepository.save(conselho);

        return conselho;
    }

}
