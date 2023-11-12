package tutorial.view;

import component.ModelFunctionFactory;
import jakarta.enterprise.context.Conversation;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import skill.model.SkillModel;
import skill.service.SkillService;
import tutorial.model.TutorialCreateModel;
import tutorial.service.TutorialService;

import java.io.Serializable;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * View bean for rendering single character create form.
 */
@ViewScoped
@Named
@Log
@NoArgsConstructor(force = true)
public class TutorialCreate implements Serializable {

    /**
     * Service for managing characters.
     */
    private final TutorialService tutorialService;

    /**
     * Service for managing professions.
     */
    private final SkillService skillService;

    /**
     * Factory producing functions for conversion between models and entities.
     */
    private final ModelFunctionFactory factory;

    /**
     * Character exposed to the view.
     */
    @Getter
    private TutorialCreateModel tutorial;

    /**
     * Available professions.
     */
    @Getter
    private List<SkillModel> skills;

    @Inject
    public TutorialCreate(
            TutorialService tutorialService,
            SkillService skillService,
            ModelFunctionFactory factory
    ) {
        this.tutorialService = tutorialService;
        this.factory = factory;
        this.skillService = skillService;
    }

    /**
     * In order to prevent calling service on different steps of JSF request lifecycle, model property is cached within
     * field and initialized during init of the view. @PostConstruct method is called after h:form header is already
     * rendered. Conversation should be started in f:metadata/f:event.
     */
    public void init() {
        skills = skillService.findAll().stream()
                .map(factory.skillToModel())
                .collect(Collectors.toList());
        tutorial = TutorialCreateModel.builder()
                .id(UUID.randomUUID())
                .build();
    }

    /**
     * Cancels character creation process.
     *
     * @return characters list navigation case
     */
    public String cancelAction() {
        return "/skill/skill_list.xhtml?faces-redirect=true";
    }

    public String saveAction() {
        tutorialService.create(factory.modelToTutorial().apply(tutorial));
        return "/skill/skill_list.xhtml?faces-redirect=true";
    }

}
