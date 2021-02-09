package com.github.alcemirjunior.dscatalog.repositories;

import com.github.alcemirjunior.dscatalog.entities.Product;
import com.github.alcemirjunior.dscatalog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
