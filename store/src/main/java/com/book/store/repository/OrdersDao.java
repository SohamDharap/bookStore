package com.book.store.repository;

import com.book.store.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrdersDao extends JpaRepository<Orders, Long> {
    List<Orders> findByUserId(Integer userId);
}