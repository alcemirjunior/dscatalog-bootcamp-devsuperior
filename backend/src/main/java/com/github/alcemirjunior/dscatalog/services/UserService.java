package com.github.alcemirjunior.dscatalog.services;

import com.github.alcemirjunior.dscatalog.dto.CategoryDTO;
import com.github.alcemirjunior.dscatalog.dto.RoleDTO;
import com.github.alcemirjunior.dscatalog.dto.UserDTO;
import com.github.alcemirjunior.dscatalog.dto.UserInsertDto;
import com.github.alcemirjunior.dscatalog.entities.Category;
import com.github.alcemirjunior.dscatalog.entities.Role;
import com.github.alcemirjunior.dscatalog.entities.User;
import com.github.alcemirjunior.dscatalog.repositories.CategoryRepository;
import com.github.alcemirjunior.dscatalog.repositories.RoleRepository;
import com.github.alcemirjunior.dscatalog.repositories.UserRepository;
import com.github.alcemirjunior.dscatalog.services.exceptions.DatabaseException;
import com.github.alcemirjunior.dscatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public Page<UserDTO> findAllPaged(PageRequest pagedRequest){
        Page<User> list = repository.findAll(pagedRequest);
        return list.map(x -> new UserDTO(x));

    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id){
        Optional<User> obj = repository.findById(id);
        User entity = obj.orElseThrow(()-> new ResourceNotFoundException("Entity not found"));
        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO insert(UserInsertDto dto) {
        User entity = new User();
        copyDtoToEntity(dto, entity);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity = repository.save(entity);
        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO update(Long id, UserDTO dto) {
        try {
            User entity = repository.getOne(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new UserDTO(entity);
        } 
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id not found" + id);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw  new ResourceNotFoundException("Id not found: " + id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Integrirt violation");
        }
    }

    private void copyDtoToEntity(UserDTO dto, User entity) {
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());

        entity.getRoles().clear();
        for(RoleDTO roleDTO : dto.getRoles()){
            Role role = roleRepository.getOne(roleDTO.getId());
            entity.getRoles().add(role);
        }
    }
}