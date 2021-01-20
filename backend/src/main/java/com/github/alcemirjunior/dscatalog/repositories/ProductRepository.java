package com.github.alcemirjunior.dscatalog.repositories;

import com.github.alcemirjunior.dscatalog.entities.Category;
import com.github.alcemirjunior.dscatalog.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
