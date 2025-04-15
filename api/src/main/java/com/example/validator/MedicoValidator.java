package com.example.validator;

import com.example.Enums.EnumStatus;
import com.example.entities.Medico;
import com.example.entities.Template;
import com.example.repositories.TemplateRepository;
import com.example.validator.generic.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MedicoValidator extends BaseValidator<Medico> {

    @Autowired
    private TemplateRepository templateRepository;

    @Override
    public void validate(Medico entity) {
        validateNotNull(entity.getConselho(), "conselho");
        validateNotEmpty(entity.getNome(), "nome");
        validateNotNull(entity.getTipo(), "tipo");
        validateNotNull(entity.getUser(), "usuario");
        validateLength(entity.getNome(), "nome", 2, 50);
    }

    public void validateInsert(Medico entity){
        validate(entity);
    }

    public void validateInativation(Medico entity){
        validateMedicoHasTemplates(entity);
    }


    private void validateMedicoHasTemplates(Medico entity){
        List<Template> templates = templateRepository.findByMedicosIdAndStatus(entity.getId(), EnumStatus.ATIVO);
        if(templates.size() > 0)
            throw new IllegalArgumentException("Este médico não pode ser excluído pois tem templates cadastrados!");
    }

}
