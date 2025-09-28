package com.book.store.repository;

import com.book.store.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
// Renamed to RoleRepository for convention
public interface RolesDao extends JpaRepository<Role, Integer> {

    // Method name must match the 'name' field in the Role entity
    Optional<Role> findByRoleName(String name);
}