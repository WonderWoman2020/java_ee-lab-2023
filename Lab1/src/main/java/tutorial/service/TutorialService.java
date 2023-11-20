package tutorial.service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import skill.repository.api.SkillRepository;
import tutorial.entity.Tutorial;
import tutorial.repository.api.TutorialRepository;
import user.repository.api.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestScoped
@NoArgsConstructor(force = true)
public class TutorialService {

    private final TutorialRepository repository;

    private final UserRepository userRepository;

    private final SkillRepository skillRepository;
    @Inject
    public TutorialService(TutorialRepository repository, UserRepository userRepository, SkillRepository skillRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.skillRepository = skillRepository;
    }
    public Optional<Tutorial> find(UUID id)
    {
        return repository.find(id);
    }
    public List<Tutorial> findAll()
    {
        return repository.findAll();
    }
    @Transactional
    public void create(Tutorial tutorial)
    {
        repository.create(tutorial);
    }
    @Transactional
    public void update(Tutorial tutorial)
    {
        repository.update(tutorial);
    }
    @Transactional
    public void delete(UUID id)
    {
        repository.delete(repository.find(id).orElseThrow());
    }

    public Optional<List<Tutorial>> findAllBySkill(UUID id) {
        return skillRepository.find(id)
                .map(repository::findAllBySkill);
    }

    public Optional<List<Tutorial>> findAllByUser(UUID id) {
        return userRepository.find(id)
                .map(repository::findAllByUser);
    }

    public Optional<Tutorial> findBySkillAndId(UUID skillId, UUID tutorialId)
    {
        return repository.findBySkillAndId(
                skillRepository.find(skillId).orElseThrow(),
                tutorialId);
    }

}
