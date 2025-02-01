package ru.cft.template.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.cft.template.core.entity.Wallet;

@Repository
public interface WalletRepository extends CrudRepository<Wallet, Integer> {
}
