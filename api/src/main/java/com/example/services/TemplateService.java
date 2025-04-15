package com.example.services;

import com.example.Enums.EnumStatus;
import com.example.dto.TemplateDto;
import com.example.entities.Medico;
import com.example.entities.Template;
import com.example.entities.User;
import com.example.mapper.EntityDtoMapper;
import com.example.repositories.MedicoRepository;
import com.example.repositories.TemplateRepository;
import com.example.utils.PartialMergeUtil;
import com.example.validator.TemplateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateService {
    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private EntityDtoMapper mapper;

    @Autowired
    private TemplateValidator templateValidator;

    public TemplateDto createTemplate(TemplateDto templateDto) {
        User usuarioLogado = userService.getUsuarioLogado();

        Medico medico = medicoRepository.findByUserAndStatus(usuarioLogado, EnumStatus.ATIVO)
                .orElseThrow(() -> new IllegalArgumentException("Médico não encontrado para o usuário logado"));

        Template template = (Template) mapper.dtoToEntity(templateDto, Template.class);
        template.setStatus(EnumStatus.ATIVO);
        template.getMedicos().add(medico);
        medico.getTemplates().add(template);

        templateValidator.validateInsert(template);

        template = templateRepository.save(template);

        return (TemplateDto) mapper.entityToDto(template, TemplateDto.class);
    }

    public List<TemplateDto> findByMedicoLogado() {
        User user = userService.getUsuarioLogado();

        Medico medico = medicoRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("Médico não encontrado"));

        List<Template> templates = templateRepository.findByMedicosIdAndStatus(medico.getId(), EnumStatus.ATIVO);

        return mapper.entityToDtoList(templates, TemplateDto.class);
    }

    public TemplateDto findById(Integer id){
        Template template = templateRepository.findByIdAndStatus(id, EnumStatus.ATIVO)
                .orElseThrow(() -> new IllegalArgumentException("Não foi encontrado template com o id " + id));

        return (TemplateDto) mapper.entityToDto(template, TemplateDto.class);
    }

    public TemplateDto update(Integer id, TemplateDto templateDto){
        Template templateOld = templateRepository.findByIdAndStatus(id, EnumStatus.ATIVO)
                .orElseThrow(() -> new IllegalArgumentException("Não foi encontrado template com id " + id));

        Template templateNew = (Template) mapper.dtoToEntity(templateDto, Template.class);

        PartialMergeUtil.merge(templateOld, templateNew);

        templateValidator.validateUpdate(templateNew);

        templateNew = templateRepository.save(templateNew);

        return (TemplateDto) mapper.entityToDto(templateNew, TemplateDto.class);
    }
}
