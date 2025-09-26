package com.book.store.repository;

import com.book.store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDao extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}