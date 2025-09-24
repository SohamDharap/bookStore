package com.book.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
@NoAArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name="order_date", nullable = false,updatable = false)
    private LocalDateTime orderDate;

    @Column(name = "status", nullable = false)
    private String status;
    
    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

}