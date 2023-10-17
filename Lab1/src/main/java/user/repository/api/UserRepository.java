package user.repository.api;

import repository.api.Repository;
import user.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends Repository<User, UUID> {

    Optional<User> findByLogin(String login);
}
