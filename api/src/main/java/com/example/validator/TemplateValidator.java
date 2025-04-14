package com.example.validator;

import com.example.entities.Template;
import com.example.validator.generic.BaseValidator;
import org.springframework.stereotype.Component;

@Component
public class TemplateValidator extends BaseValidator<Template> {
    @Override
    public void validate(Template entity) {
        validateNotEmpty(entity.getDescricao(), "descrição");
        validateNotEmpty(entity.getConteudo(), "conteúdo");
        validateNotNull(entity.getTipo(), "tipo");
        validateLength(entity.getDescricao(), "descrição", 2, 50);
        validateLength(entity.getConteudo(), "conteúdo", 2, 5000);
    }

    public void validateInsert(Template entity){
        validate(entity);
    }
}
