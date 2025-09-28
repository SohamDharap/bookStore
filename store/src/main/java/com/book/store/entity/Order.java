package com.book.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "td_orders")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne 
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name="order_date", nullable = false,updatable = false)
    private LocalDateTime orderDate;

    @Column(name = "status", nullable = false)
    private String status;
    
    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

}