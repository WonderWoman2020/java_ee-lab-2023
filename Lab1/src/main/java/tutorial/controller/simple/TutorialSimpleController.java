package tutorial.controller.simple;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import tutorial.controller.api.TutorialController;
import tutorial.dto.GetTutorialsResponse;
import tutorial.dto.function.TutorialsToResponseFunction;
import tutorial.service.TutorialService;

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
}
