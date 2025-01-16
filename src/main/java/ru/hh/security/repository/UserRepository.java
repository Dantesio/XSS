package ru.hh.security.repository;

import java.util.Optional;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import ru.hh.security.model.User;

@Repository
public interface UserRepository extends ListCrudRepository<User, Long> {
  Optional<User> findByLogin(String email);
}