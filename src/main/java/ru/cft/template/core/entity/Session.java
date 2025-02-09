package ru.cft.template.core.entity;

import jakarta.persistence.*;
import javax.validation.constraints.*;
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
    @Column(name = "id")
    private UUID id;

    @NotNull(message = "UserId - это обязательное поле.")
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @NotNull(message = "ExpirationTime - это обязательное поле.")
    @Column(name = "expiration_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationTime;

    @NotNull(message = "Active - это обязательное поле.")
    @Column(name = "active", nullable = false)
    private Boolean active;

}
