package tutorial.controller.api;

import tutorial.dto.GetTutorialResponse;
import tutorial.dto.GetTutorialsResponse;

import java.util.UUID;

public interface TutorialController {

    GetTutorialsResponse getTutorials();

    GetTutorialResponse getTutorial(UUID uuid);

    void deleteTutorial(UUID id);
}
