package com.transportWala.tw_user.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String name;
    private String email;
    private String phoneNumber;

    // Additional user-specific fields
}
