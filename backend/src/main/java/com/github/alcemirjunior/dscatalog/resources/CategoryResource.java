package com.github.alcemirjunior.dscatalog.resources;

import com.github.alcemirjunior.dscatalog.entities.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    //primeira rota (quando vem a requesição)
    //primeiro contato com o front-end

    @GetMapping
    public ResponseEntity<List<Category>> findAll(){
        List<Category> list = new ArrayList<>();
        list.add(new Category(1L, "books"));
        list.add(new Category(2L, "eletronics"));
        return ResponseEntity.ok().body(list);
    }

}
