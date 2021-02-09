package com.github.alcemirjunior.dscatalog.repositories;

import com.github.alcemirjunior.dscatalog.entities.Role;
import com.github.alcemirjunior.dscatalog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
