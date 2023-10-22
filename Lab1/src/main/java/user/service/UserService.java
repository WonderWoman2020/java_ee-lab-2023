package user.service;

import user.entity.User;
import user.repository.api.UserRepository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public void updateAvatar(UUID id, InputStream is) {

        try {
            Files.write(Path.of("avatar.txt"), Path.of("avatar.txt").toAbsolutePath().toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /*repository.find(id).ifPresent(user -> {
            try {
                //user.setAvatar(is.readAllBytes());
                repository.update(user);
            } catch (IOException ex) {
                throw new IllegalStateException(ex);
            }
        });*/
    }

    public byte[] findAvatar(UUID id)
    {
        /*InputStream is = this.getClass().getClassLoader().getResourceAsStream("/configuration/avatar/"+id.toString()+".png");
        try {
            return is.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
        try {
            return Files.readAllBytes(Path.of("avatar.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
