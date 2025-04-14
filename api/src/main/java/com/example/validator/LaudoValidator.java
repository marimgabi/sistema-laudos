package com.example.validator;

import com.example.Enums.EnumStatus;
import com.example.Enums.EnumTipoMedico;
import com.example.entities.Laudo;
import com.example.entities.Medico;
import com.example.entities.User;
import com.example.repositories.MedicoRepository;
import com.example.services.UserService;
import com.example.validator.generic.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LaudoValidator extends BaseValidator<Laudo> {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private UserService userService;

    @Override
    public void validate(Laudo entity) {
        validateNotEmpty(entity.getConteudo(), "conteúdo");
        validateNotEmpty(entity.getNomePaciente(), "nome do paciente");
        validateNotNull(entity.getSexo(), "sexo");
        validateNotNull(entity.getDataNascimento(), "data de nascimento");
        validateNotNull(entity.getMedicoExecutante(), "médico executante");
        validateNotNull(entity.getMedicoSolicitante(), "médico solicitante");
        validateLength(entity.getNomePaciente(), "nome paciente",2, 50);
        validateLength(entity.getConteudo(), "conteúdo",2, 5000);
    }

    public void validateInsert(Laudo entity){
        validate(entity);
        validateMedicoSolicitante(entity);
        validateMedicoExecutante(entity);
    }

    public void validateUpdate(Laudo entity){
        validate(entity);
        validateMedicoSolicitante(entity);
        validateMedicoExecutante(entity);
        validateUpdateMedicoExecutante();
    }

    private void validateMedicoSolicitante(Laudo entity){
        Medico medico = medicoRepository.findByIdAndStatus(entity.getMedicoSolicitante().getId(), EnumStatus.ATIVO)
                .orElseThrow(() -> new IllegalArgumentException("Não foi encontrado médico com o id: " + entity.getMedicoSolicitante().getId()));

        entity.setMedicoSolicitante(medico);

        if(medico.getTipo() != EnumTipoMedico.SOLICITANTE)
            throw new IllegalArgumentException("O médico solicitante informado não é do tipo solicitante!");
    }

    private void validateMedicoExecutante(Laudo entity){
        Medico medico = medicoRepository.findByIdAndStatus(entity.getMedicoExecutante().getId(), EnumStatus.ATIVO)
                .orElseThrow(() -> new IllegalArgumentException("Não foi encontrado médico com o id: " + entity.getMedicoExecutante().getId()));

        entity.setMedicoExecutante(medico);

        if(medico.getTipo() != EnumTipoMedico.EXECUTANTE)
            throw new IllegalArgumentException("O médico executante informado não é do tipo executante!");
    }

    private void validateUpdateMedicoExecutante(){
        User user = userService.getUsuarioLogado();

        Medico medico = medicoRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("Médico não encontrado"));

        if(medico.getTipo() != EnumTipoMedico.EXECUTANTE)
            throw new IllegalArgumentException("Essa ação só pode ser realizada por um médico executante!");
    }

}
