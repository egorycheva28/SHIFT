package ru.cft.template.core.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.cft.template.core.exception.BalanceException;

@Entity
@Table(name = "wallets")
@Getter
@Setter
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer number;

    @Column(nullable = false)
    private Long balance;

    public void minusAmount(Long amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            throw new BalanceException("Недостаточно средств на счету");
        }
    }

    public void plusAmount(Long amount) {
        balance += amount;
    }
}
