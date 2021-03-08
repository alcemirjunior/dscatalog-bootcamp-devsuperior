package com.github.alcemirjunior.dscatalog.tests.factory;

import com.github.alcemirjunior.dscatalog.dto.ProductDTO;
import com.github.alcemirjunior.dscatalog.entities.Category;
import com.github.alcemirjunior.dscatalog.entities.Product;

import java.time.Instant;

public class ProductFactory {
    public static Product createProduct(){
        Product product = new Product(1L, "Phone", "Good Phone", 800.0, "https://img.com/img.png", Instant.parse("2020-10-20T03:00:00Z"));
        product.getCategories().add(new Category(1L,null));
        return product;
    }
    public static ProductDTO createProductDTO(){
        Product product = createProduct();
        return new ProductDTO(product,product.getCategories());
    }

    public static ProductDTO createProductDTO(Long id){
        ProductDTO dto = createProductDTO();
        dto.setId(id);
        return dto;
    }
}
