package ru.cft.template.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.cft.template.entity.User;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {

}
