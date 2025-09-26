package com.book.store.repository;
import java.util.List;
import com.book.store.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrdersDao extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Integer userId);
}