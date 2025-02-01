package ru.cft.template.core.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wallet_id", referencedColumnName = "number")
    private Wallet wallet;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String firstName;

    private String middleName;

    @Column(unique = true, nullable = false)
    private Long phone;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private Date birthday;

    @Column(nullable = false)
    private String password;

}
