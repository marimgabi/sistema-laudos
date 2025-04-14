package com.example.validator;

import com.example.Enums.EnumStatus;
import com.example.entities.Medico;
import com.example.entities.User;
import com.example.repositories.MedicoRepository;
import com.example.repositories.UserRepository;
import com.example.validator.generic.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserValidator extends BaseValidator<User> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public void validate(User entity) {
        validateFields(entity);
    }

    public void validadeInsert(User entity){
        validate(entity);
        validateUniqueUsername(entity);
        validateUniqueEmail(entity);
        validateMedicoIsNotAdmin(entity);
        validateUniqueMedico(entity);
    }

    private void validateFields(User entity){
        validateNotEmpty(entity.getEmail(), "email");
        validateNotEmpty(entity.getUsername(), "username");
        validateNotEmpty(entity.getPassword(), "password");
    }

    private void validateUniqueUsername(User entity){
        Optional<User> user = userRepository.findByUsernameAndStatus(entity.getUsername(), EnumStatus.ATIVO);
        if(user.isPresent()){
            throw new IllegalArgumentException("Já existe um usuário com este username!");
        }
    }

    private void validateUniqueEmail(User entity){
        Optional<User> user = userRepository.findByEmailAndStatus(entity.getEmail(), EnumStatus.ATIVO);
        if(user.isPresent()){
            throw new IllegalArgumentException("Já existe um usuário com este username!");
        }
    }

    private void validateUniqueMedico(User entity){
        Optional<Medico> medico = medicoRepository.findByUser(entity.getUsername(), entity.getEmail(), EnumStatus.ATIVO);
        if(medico.isPresent()){
            throw new IllegalArgumentException("Já existe um médico cadastrado com este username e/ou email");
        }
    }

    private void validateMedicoIsNotAdmin(User entity){
        if(entity.getRole().getRole().equals("ADMIN") && !entity.getMedicos().isEmpty()){
            throw new IllegalArgumentException("Um médico não pode ter a role de ADMIN!");
        }
    }

}
