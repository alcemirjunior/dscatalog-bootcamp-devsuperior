package com.github.alcemirjunior.dscatalog.tests.repositories;

import com.github.alcemirjunior.dscatalog.entities.Category;
import com.github.alcemirjunior.dscatalog.entities.Product;
import com.github.alcemirjunior.dscatalog.repositories.ProductRepository;
import com.github.alcemirjunior.dscatalog.tests.factory.ProductFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository repository;

    private long existingId;
    private long nonExistingId;
    private long countTotalProducts;
    private long countPCGamerProducts;
    private long countCategory3Products;
    private PageRequest pageRequest;

    @BeforeEach
    void setUp() throws Exception{
        existingId = 1L;
        nonExistingId = 1000L;
        countTotalProducts = 25L;
        countPCGamerProducts = 21L;
        countCategory3Products = 23L;
        pageRequest = PageRequest.of(0,10);
    }

    @Test
    public void findShouldReturnOnlySelectedCategoryWhenCategoryInformed(){

        List<Category> categories = new ArrayList<>();
        categories.add(new Category(3L,null));

        Page<Product> result = repository.find(categories,"", pageRequest);

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(countCategory3Products, result.getTotalElements());
    }

    @Test
    public void findShouldReturnAllProductsWhenCategoryNotInformed(){

        List<Category> categories = null;

        Page<Product> result = repository.find(categories,"", pageRequest);

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(countTotalProducts, result.getTotalElements());
    }

    @Test
    public void findShouldReturnNothingWhenNameDoesNotExist(){
        String name = "Camera";
        Page<Product> result = repository.find(null, name, pageRequest);
        Assertions.assertTrue(result.isEmpty());
    }


    @Test
    public void findShouldReturnAllProductsWhenNameIsEmpty(){

        String name = "";

        Page<Product> result = repository.find(null,name, pageRequest);

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(countTotalProducts, result.getTotalElements());
    }

    @Test
    public void findShouldReturnProductsWhenNameExistsIgnoringCase(){

        String name = "pc gAMeR";

        Page<Product> result = repository.find(null,name, pageRequest);

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(countPCGamerProducts, result.getTotalElements());
    }

    @Test
    public void findShouldReturnProductsWhenNameExists(){

        String name = "PC Gamer";

        Page<Product> result = repository.find(null,name, pageRequest);

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(countPCGamerProducts, result.getTotalElements());
    }

    @Test
    public void SaveShouldPersistWithAutoincrementWhenIdIsNull(){

        Product product = ProductFactory.createProduct();
        product.setId(null);

        product = repository.save(product);
        Optional<Product> result = repository.findById(product.getId());

        Assertions.assertNotNull(product.getId());
        Assertions.assertEquals(countTotalProducts + 1L, product.getId());
        Assertions.assertTrue(result.isPresent());
        Assertions.assertSame(result.get(),product); //para saber se dois objetos sao iguais usa-se assertSame();

    }

    @Test
    public void deleteShouldDeleteObjectWhenIdExists(){

        repository.deleteById(existingId);
        Optional<Product> result = repository.findById(existingId);
        Assertions.assertFalse(result.isPresent());
    }


    @Test
    public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist(){

        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            repository.deleteById(nonExistingId);
        });
    }

}
