package tutorial.controller.simple;

import controller.exception.NotFoundException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import tutorial.controller.api.TutorialController;
import tutorial.dto.GetTutorialResponse;
import tutorial.dto.GetTutorialsResponse;
import tutorial.dto.function.TutorialToResponseFunction;
import tutorial.dto.function.TutorialsToResponseFunction;
import tutorial.service.TutorialService;
import user.dto.function.UserToResponseFunction;

import java.util.UUID;

@Alternative
@RequestScoped
public class TutorialSimpleController implements TutorialController {

    private TutorialService service;

    @Inject
    public TutorialSimpleController(TutorialService service) {
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
        return null;
    }

    @Override
    public GetTutorialResponse getTutorialBySkill(UUID skillId, UUID tutorialId) {
        return null;
    }
}
