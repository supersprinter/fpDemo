package com.finnplay.demo.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User
{
    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false, length = 16)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator( name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Instant birthday;

}
