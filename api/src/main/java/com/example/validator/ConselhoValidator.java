package com.example.validator;

import com.example.Enums.EnumStatus;
import com.example.entities.Conselho;
import com.example.repositories.ConselhoRepository;
import com.example.validator.generic.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ConselhoValidator extends BaseValidator<Conselho> {

    @Autowired
    private ConselhoRepository conselhoRepository;

    @Override
    public void validate(Conselho entity) {
        validateNotEmpty(entity.getNumero(), "numero");
        validateNotNull(entity.getTipo(), "tipo");
        validateNotNull(entity.getEstado(), "estado");
    }

    public void validateInsert(Conselho entity){
        validate(entity);
        validateConselhoExists(entity);
    }

    private void validateConselhoExists(Conselho entity){
        Optional<Conselho> conselho = conselhoRepository.findByNumeroAndEstadoAndTipoAndStatus(
                entity.getNumero(), entity.getEstado(), entity.getTipo(), EnumStatus.ATIVO
        );
        if(conselho.isPresent())
            throw new IllegalArgumentException("O registro do conselho já existe!");
    }
}
