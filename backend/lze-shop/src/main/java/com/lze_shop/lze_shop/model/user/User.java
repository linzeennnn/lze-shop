package com.lze_shop.lze_shop.model.user;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // bigint(20), auto_increment, primary key

    @Column(nullable = false, unique = true, length = 50)
    private String username; // varchar(50), not null, unique

    @Column(nullable = false, length = 255)
    private String password; // varchar(255), not null

    @Column(length = 100)
    private String email; // varchar(100), nullable

    @Column(length = 20)
    private String phone; // varchar(20), nullable

    @Column
    private Integer status; // int(11), nullable

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private LocalDateTime createdAt; // timestamp, default current_timestamp

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt; // timestamp, auto-update

    @Column(columnDefinition = "BIGINT DEFAULT 0")
    private Long exp; // bigint(20), default 0
}
