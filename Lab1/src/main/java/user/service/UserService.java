package user.service;

import user.entity.User;
import user.repository.api.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }
    public Optional<User> find(UUID id)
    {
        return repository.find(id);
    }
    public void find(String login)
    {
        repository.findByLogin(login);
    }
    public List<User> findAll()
    {
        return repository.findAll();
    }
    public void create(User user)
    {
        repository.create(user);
    }
    public void update(User user)
    {
        repository.update(user);
    }
    public void delete(User user)
    {
        repository.delete(user);
    }
    
}
