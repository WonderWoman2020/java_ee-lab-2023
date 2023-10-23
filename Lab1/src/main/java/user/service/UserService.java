package user.service;

import user.entity.User;
import user.repository.api.UserRepository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserService {

    private final UserRepository repository;

    private final String avatarsUploadPath;

    public UserService(UserRepository repository, String avatarsUploadPath) {
        this.repository = repository;
        this.avatarsUploadPath = avatarsUploadPath;
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
            Files.createDirectories(Path.of(this.avatarsUploadPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Files.write(Path.of(this.avatarsUploadPath+id.toString()+".png"), is.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] findAvatar(UUID id)
    {
        if(!Files.exists(Path.of(this.avatarsUploadPath+id.toString()+".png")))
            return null;
        try {
            return Files.readAllBytes(Path.of(this.avatarsUploadPath+id.toString()+".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAvatar(UUID id)
    {
        if(!Files.exists(Path.of(this.avatarsUploadPath+id.toString()+".png")))
            return;
        try {
            Files.delete(Path.of(this.avatarsUploadPath + id.toString() + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
