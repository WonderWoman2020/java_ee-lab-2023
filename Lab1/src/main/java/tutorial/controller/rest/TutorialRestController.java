package tutorial.controller.rest;

//import controller.exception.NotFoundException;
import jakarta.ws.rs.NotFoundException;
import jakarta.enterprise.inject.Alternative;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import tutorial.controller.api.TutorialController;
import tutorial.dto.GetTutorialResponse;
import tutorial.dto.GetTutorialsResponse;
import tutorial.dto.function.TutorialToResponseFunction;
import tutorial.dto.function.TutorialsToResponseFunction;
import tutorial.service.TutorialService;

import java.util.UUID;

@Alternative
@Path("")//Annotation required by the specification.
public class TutorialRestController implements TutorialController {

    private TutorialService service;

    @Inject
    public TutorialRestController(TutorialService service) {
        this.service = service;
    }

    @Override
    public GetTutorialsResponse getTutorials() {
        return new TutorialsToResponseFunction().apply(service.findAll());
    }

    @Override
    public GetTutorialResponse getTutorial(UUID uuid) {
        return service.find(uuid).map(new TutorialToResponseFunction())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void deleteTutorial(UUID id) {
        service.find(id).ifPresentOrElse(
                entity -> service.delete(entity.getId()),
                () -> {
                    throw new NotFoundException();
                }
        );
    }

    @Override
    public GetTutorialsResponse getTutorialsBySkill(UUID skillId) {
        return new TutorialsToResponseFunction().apply(service.findAllBySkill(skillId)
                .orElseThrow(NotFoundException::new));
    }

    @Override
    public GetTutorialResponse getTutorialBySkill(UUID skillId, UUID tutorialId) {
        return new TutorialToResponseFunction().apply(service.findBySkillAndId(skillId, tutorialId)
                .orElseThrow(NotFoundException::new));
    }

    @Override
    public void deleteTutorialBySkill(UUID skillId, UUID tutorialId) {
        service.findBySkillAndId(skillId, tutorialId).ifPresentOrElse(
                entity -> service.delete(entity.getId()),
                () -> {
                    throw new NotFoundException();
                }
        );
    }
}
