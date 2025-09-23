package com.book.store.repository;

import com.book.store.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDao extends JpaRepository<Users, Long> {
    Users findbyemail(String email);
}