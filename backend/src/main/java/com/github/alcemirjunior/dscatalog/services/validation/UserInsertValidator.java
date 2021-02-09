package com.github.alcemirjunior.dscatalog.services.validation;

import com.github.alcemirjunior.dscatalog.dto.UserInsertDTO;
import com.github.alcemirjunior.dscatalog.entities.User;
import com.github.alcemirjunior.dscatalog.repositories.UserRepository;
import com.github.alcemirjunior.dscatalog.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {

    @Autowired
    private UserRepository repository;

    @Override
    public void initialize(UserInsertValid ann) {
    }

    @Override
    public boolean isValid(UserInsertDTO dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        // Coloque aqui seus testes de validação, acrescentando objetos FieldMessage à lista

        User user = repository.findByEmail(dto.getEmail());
        if (user != null){
            list.add(new FieldMessage("email","Email já existe"));
        }


        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getfieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}