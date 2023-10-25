package skill.service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import skill.entity.Skill;
import skill.repository.api.SkillRepository;
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

@RequestScoped
@NoArgsConstructor(force = true)
public class SkillService {

    private final SkillRepository repository;
    @Inject
    public SkillService(SkillRepository repository) {
        this.repository = repository;
    }
    public Optional<Skill> find(UUID id)
    {
        return repository.find(id);
    }
    public List<Skill> findAll()
    {
        return repository.findAll();
    }
    public void create(Skill skill)
    {
        repository.create(skill);
    }
    public void update(Skill skill)
    {
        repository.update(skill);
    }
    public void delete(Skill skill)
    {
        repository.delete(skill);
    }

}
