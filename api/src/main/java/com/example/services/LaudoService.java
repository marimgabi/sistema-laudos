package com.example.services;

import com.example.Enums.EnumStatus;
import com.example.Enums.EnumTipoMedico;
import com.example.dto.LaudoDto;
import com.example.entities.Laudo;
import com.example.entities.Medico;
import com.example.entities.User;
import com.example.mapper.EntityDtoMapper;
import com.example.repositories.LaudoRepository;
import com.example.repositories.MedicoRepository;
import com.example.utils.PartialMergeUtil;
import com.example.validator.LaudoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LaudoService {

    @Autowired
    private LaudoRepository laudoRepository;

    @Autowired
    private EntityDtoMapper mapper;

    @Autowired
    private LaudoValidator laudoValidator;

    @Autowired
    private UserService userService;

    @Autowired
    private MedicoRepository medicoRepository;

    public LaudoDto create(LaudoDto laudoDto){
        Laudo laudo = (Laudo) mapper.dtoToEntity(laudoDto, Laudo.class);

        User user = userService.getUsuarioLogado();

        Medico medico = medicoRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("Médico não encontrado"));

        laudo.setMedicoExecutante(medico);
        laudo.setStatus(EnumStatus.ATIVO);

        laudoValidator.validateInsert(laudo);

        laudo = laudoRepository.save(laudo);

        return (LaudoDto) mapper.entityToDto(laudo, LaudoDto.class);
    }

    public LaudoDto update(LaudoDto laudoDto, Integer id){
        Laudo laudoNew = (Laudo) mapper.dtoToEntity(laudoDto, LaudoDto.class);

        Laudo laudoOld = laudoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não foi encontrado laudo com id " + laudoNew.getId()));

        PartialMergeUtil.merge(laudoOld, laudoNew);

        laudoValidator.validateUpdate(laudoNew);

        laudoRepository.save(laudoNew);

        return (LaudoDto) mapper.entityToDto(laudoNew, LaudoDto.class);
    }

    public List<LaudoDto> findAllByUser(){
        User user = userService.getUsuarioLogado();

        if(user.getRole().getRole().equals("ADMIN")){
            return findAll();
        }

        if(user.getRole().getRole().equals("MEDICO")){
            Medico medico = medicoRepository.findByUser(user)
                    .orElseThrow(() -> new IllegalArgumentException("Médico não encontrado"));

            return findAllByMedico(medico);
        }

        throw new IllegalArgumentException("Não foi encontrado usuário logado");
    }

    public List<LaudoDto> findAll(){
        List<Laudo> laudos = laudoRepository.findAllByStatus(EnumStatus.ATIVO);
        return mapper.entityToDtoList(laudos, LaudoDto.class);
    }

    public List<LaudoDto> findAllByMedico(Medico medico){
        List<Laudo> laudos = new ArrayList<>();

        if(medico.getTipo() == EnumTipoMedico.EXECUTANTE){
            laudos = laudoRepository.findByMedicoExecutanteAndStatus(medico, EnumStatus.ATIVO);
        }else{
            laudos = laudoRepository.findByMedicoSolicitanteAndStatus(medico, EnumStatus.ATIVO);
        }

        return mapper.entityToDtoList(laudos, LaudoDto.class);
    }
}
