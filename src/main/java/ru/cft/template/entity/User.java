package ru.cft.template.entity;

import com.jayway.jsonpath.internal.function.numeric.Min;
import jakarta.persistence.*;
//import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

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

    //@Size()
    //@Min(value = 1, message="n")
    @Column(nullable = false)
    private String lastName;

    //@NotNull
    @Column(nullable = false)
    private String firstName;

    private String middleName;

    @Column(nullable = false)
    private Long phone;

    @Column(unique = true, nullable = false)
    //@Email(message = "Invalid email")
    private String email;

    @Column(nullable = false)
    private Date birthday;

    @Column(nullable = false)
    private String password;

}
