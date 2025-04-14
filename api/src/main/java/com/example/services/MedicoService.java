package com.example.services;

import com.example.Enums.EnumStatus;
import com.example.dto.MedicoDto;
import com.example.entities.Conselho;
import com.example.entities.Medico;
import com.example.entities.User;
import com.example.mapper.EntityDtoMapper;
import com.example.repositories.MedicoRepository;
import com.example.validator.MedicoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EntityDtoMapper mapper;

    @Autowired
    private MedicoValidator medicoValidator;

    @Autowired
    private ConselhoService conselhoService;

    public MedicoDto create(MedicoDto medicoDto, User user){
        Medico medico = (Medico) mapper.dtoToEntity(medicoDto, Medico.class);

        medico.setStatus(EnumStatus.ATIVO);
        medico.setUser(user);

        Conselho conselho = conselhoService.create(medico.getConselho());

        medicoValidator.validateInsert(medico);

        medico = medicoRepository.save(medico);

        return (MedicoDto) mapper.entityToDto(medico, MedicoDto.class);
    }

}
