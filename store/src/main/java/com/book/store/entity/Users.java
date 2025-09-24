package com.book.store.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "td_user_map")
public class Users { // Renamed from Users to User for convention

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Changed from Long to Integer

    @Column(name = "firstname", nullable = false, length = 100)
    private String firstName; // Renamed from firstname

    @Column(name = "lastname", length = 100)
    private String lastName; // Added lastName field

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "mobile_no", unique = true, length = 15)
    private String mobileNo; // Added mobileNo field

    @ManyToOne // Replaced String role with a proper relationship
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(name = "status", nullable = false, length = 50)
    private String status = "ACTIVE"; // Added status with a default value

    @CreationTimestamp
    @Column(name = "creation_date", updatable = false)
    private LocalDateTime creationDate; // Added creation timestamp

    @UpdateTimestamp
    @Column(name = "modification_date")
    private LocalDateTime modificationDate; // Added modification timestamp
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Order> orders; // Added relationship to Orders

    }