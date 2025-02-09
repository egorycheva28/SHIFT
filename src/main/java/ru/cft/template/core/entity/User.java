package ru.cft.template.core.entity;

import jakarta.persistence.*;
import javax.validation.constraints.*;
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
    @Column(name = "id")
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wallet_id", referencedColumnName = "number")
    private Wallet wallet;

    @NotNull(message = "LastName - это обязательное поле.")
    @Column(name = "last_name", nullable = false, length = 50)
    @Pattern(regexp = "^[А-ЯЁ][а-яё]{0,49}$", message = "В lastName допускаются только буквы русского алфавита, первая буква - заглавная, не более 50 символов.")
    private String lastName;

    @NotNull(message = "FirstName - это обязательное поле.")
    @Column(name = "first_name", nullable = false, length = 50)
    @Pattern(regexp = "^[А-ЯЁ][а-яё]{0,49}$", message = "В firstName допускаются только буквы русского алфавита, первая буква - заглавная, не более 50 символов.")
    private String firstName;

    @Column(name = "middle_name", length = 50)
    @Pattern(regexp = "^[А-ЯЁ][а-яё]{0,49}$", message = "В middleName допускаются только буквы русского алфавита, первая буква - заглавная, не более 50 символов.")
    private String middleName;

    @NotNull(message = "Phone - это обязательное поле.")
    @Column(name = "phone", unique = true, nullable = false, length = 11)
    //@Pattern(regexp = "^7[0-9]{10}$", message = "Phone должен состоять из 11 цифр. Первая цифра - 7. К номеру телефона может быть привязан только один пользователь.")
    private Long phone;

    @NotNull(message = "Email - это обязательное поле.")
    @Column(name = "email", unique = true, nullable = false)
    @Email(message = "Email не соответсвует стандартной маске почты.")
    private String email;

    @NotNull(message = "Birthday - это обязательное поле.")
    @Column(name = "birthday", nullable = false)
    private Date birthday;

    @NotNull(message = "Password - это обязательное поле.")
    @Column(name = "password", nullable = false, length = 64)
    @Size(min = 8, max = 64, message = "Password должен содержать от 8 до 64 символов.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!?])[a-zA-Z0-9!?]{8,64}$", message = "Password должен обязательно состоять минимум из 1 буквы латинского алфавита верхнего и нижнего регистра, знака !? и цифры.")
    private String password;

}
