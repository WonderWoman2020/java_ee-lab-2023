package user.service;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import user.entity.User;
import user.repository.api.UserRepository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;

@LocalBean
@Stateless
@NoArgsConstructor(force = true)
public class UserService {

    private final UserRepository repository;

    @Inject
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

    public void delete(UUID id)
    {
        repository.delete(repository.find(id).orElseThrow());
    }

    public void updateAvatar(UUID id, InputStream is) {
        try {
            Files.createDirectories(Path.of(this.loadAvatarsUploadPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Files.write(Path.of(this.loadAvatarsUploadPath()+id.toString()+".png"), is.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] findAvatar(UUID id)
    {
        if(!Files.exists(Path.of(this.loadAvatarsUploadPath()+id.toString()+".png")))
            return null;
        try {
            return Files.readAllBytes(Path.of(this.loadAvatarsUploadPath()+id.toString()+".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAvatar(UUID id)
    {
        if(!Files.exists(Path.of(this.loadAvatarsUploadPath()+id.toString()+".png")))
            return;
        try {
            Files.delete(Path.of(this.loadAvatarsUploadPath() + id.toString() + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String loadAvatarsUploadPath()
    {
        Properties properties = new Properties();
        try {
            InputStream is = this.getClass().getResourceAsStream("/app.config");
            properties.load(is);
            return properties.getProperty("uploadAvatarPath");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
