package com.github.alcemirjunior.dscatalog.services;

import com.github.alcemirjunior.dscatalog.entities.Category;
import com.github.alcemirjunior.dscatalog.repositories.CaterogyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CaterogyRepository repository;

    public List<Category> findAll(){
        return repository.findAll();
    }

}
