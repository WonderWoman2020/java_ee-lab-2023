package user.repository.memory;

import datastore.component.DataStore;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import user.entity.User;
import user.repository.api.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestScoped
public class UserInMemoryRepository implements UserRepository {

    private final DataStore store;

    @Inject
    public UserInMemoryRepository(DataStore store) {
        this.store = store;
    }
    @Override
    public Optional<User> find(UUID id) {
        return store.findAllUsers().stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<User> findAll() {
        return store.findAllUsers();
    }

    @Override
    public void create(User entity) {
        store.createUser(entity);
    }

    @Override
    public void update(User entity) {
        store.updateUser(entity);
    }

    @Override
    public void delete(User entity) {
        throw new UnsupportedOperationException("Operation not implemented.");
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return store.findAllUsers().stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst();
    }
}
