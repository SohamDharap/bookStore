package com.book.store.repository;


import com.book.store.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesDao extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);
}