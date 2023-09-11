package com.example.springsecurity.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String username;
    private String password;
    private String email;
    private String role;

    @CreationTimestamp
    private LocalDateTime createdBy;

    @UpdateTimestamp
    private LocalDateTime updatedBy;

}
