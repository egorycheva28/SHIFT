package ru.cft.template.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.cft.template.entity.Wallet;

import java.util.UUID;

@Repository
public interface WalletRepository extends CrudRepository<Wallet, Integer> {
}
