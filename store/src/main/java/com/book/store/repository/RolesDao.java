package com.book.store.repository;


import com.book.store.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesDao extends JpaRepository<Roles, Long> {
    Roles findByRoleName(String roleName);
}