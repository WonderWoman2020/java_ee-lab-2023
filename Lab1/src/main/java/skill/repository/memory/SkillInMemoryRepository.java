package skill.repository.memory;

import datastore.component.DataStore;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import skill.entity.Skill;
import skill.repository.api.SkillRepository;
import tutorial.entity.Tutorial;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequestScoped
public class SkillInMemoryRepository implements SkillRepository {

    private final DataStore store;
    @Inject
    public SkillInMemoryRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Skill> find(UUID id) {
        return store.findAllSkills().stream()
                .filter(skill -> skill.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Skill> findAll() {
        return store.findAllSkills();
    }

    @Override
    public void create(Skill entity) {
        store.createSkill(entity);
    }

    @Override
    public void update(Skill entity) {
        store.updateSkill(entity);
    }

    @Override
    public void delete(Skill entity) {
        store.deleteSkill(entity.getId());
        List<Tutorial> tutorials = store.findAllTutorials().stream()
                .filter(tutorial -> entity.equals(tutorial.getSkill()))
                .collect(Collectors.toList());
        for(int i=0; i<tutorials.size(); i++)
            store.deleteTutorial(tutorials.get(i).getId());
    }
}
