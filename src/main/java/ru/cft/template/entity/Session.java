package ru.cft.template.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "sessions")
@Getter
@Setter
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationTime;

    @Column(nullable = false)
    private Boolean active;

}
