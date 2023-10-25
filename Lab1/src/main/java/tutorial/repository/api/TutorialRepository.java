package tutorial.repository.api;

import repository.api.Repository;
import skill.entity.Skill;
import tutorial.entity.Tutorial;
import user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TutorialRepository extends Repository<Tutorial, UUID> {

    List<Tutorial> findAllByUser(User user);

    List<Tutorial> findAllBySkill(Skill skill);
}
