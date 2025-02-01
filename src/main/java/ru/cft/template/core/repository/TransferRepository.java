package ru.cft.template.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.cft.template.core.entity.Transfer;
import java.util.UUID;

@Repository
public interface TransferRepository extends CrudRepository<Transfer, UUID> {
}
