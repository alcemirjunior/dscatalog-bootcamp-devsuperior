package com.github.alcemirjunior.dscatalog.repositories;

import com.github.alcemirjunior.dscatalog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaterogyRepository extends JpaRepository<Category, Long> {
}
