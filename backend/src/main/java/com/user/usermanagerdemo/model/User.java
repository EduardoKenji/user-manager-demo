package com.user.usermanagerdemo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users") // specify PostgreSQL table for the entity
@Getter // automatically generate getters
@Setter // automatically generate setters
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;
}
