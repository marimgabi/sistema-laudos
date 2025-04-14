package com.example.validator;

import com.example.entities.Medico;
import com.example.validator.generic.BaseValidator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MedicoValidator extends BaseValidator<Medico> {

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
//        validateMedicoHasTemplates(entity);
    }

    //TODO refatorar para nova relação
//    private void validateMedicoHasTemplates(Medico entity){
//        List<MedicoTemplate> medicoTemplateList = medicoTemplateRepository.findAllByMedicoANdStatus(entity, EnumStatus.ATIVO);
//        if(medicoTemplateList.size() > 0)
//            throw new IllegalArgumentException("Este médico não pode ser excluído pois tem templates cadastrados!");
//    }

//    private void validateUserHasMedico(Medico entity){
//        if(entity.getUser().getMedicos().size() > 0 && entity.getUser().getMedicos().con)
//    }
}
