package tutorial.repository.memory;

import datastore.component.DataStore;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import skill.entity.Skill;
import tutorial.entity.Tutorial;
import tutorial.repository.api.TutorialRepository;
import user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequestScoped
public class TutorialInMemoryRepository implements TutorialRepository {

    private final DataStore store;

    @Inject
    public TutorialInMemoryRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Tutorial> find(UUID id) {
        return store.findAllTutorials().stream()
                .filter(tutorial -> tutorial.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Tutorial> findAll() {
        return store.findAllTutorials();
    }

    @Override
    public void create(Tutorial entity) {
        this.store.createTutorial(entity);
    }

    @Override
    public void update(Tutorial entity) {
        this.store.updateTutorial(entity);
    }

    @Override
    public void delete(Tutorial entity) {
        this.store.deleteTutorial(entity.getId());
    }

    @Override
    public List<Tutorial> findAllByUser(User user) {
        return store.findAllTutorials().stream()
                .filter(tutorial -> user.equals(tutorial.getAuthor()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Tutorial> findAllBySkill(Skill skill) {
        return store.findAllTutorials().stream()
                .filter(tutorial -> skill.equals(tutorial.getSkill()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Tutorial> findBySkillAndId(Skill skill, UUID id) {
        return findAllBySkill(skill).stream()
                .filter(tutorial -> tutorial.getId().equals(id))
                .findFirst();
    }
}
