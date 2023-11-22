package tutorial.view;

import component.ModelFunctionFactory;
import jakarta.ejb.EJB;
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
    private TutorialService tutorialService;

    /**
     * Service for managing professions.
     */
    private SkillService skillService;

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

    @EJB
    public void setTutorialService(TutorialService tutorialService)
    {
        this.tutorialService = tutorialService;
    }

    @EJB
    public void setSkillService(SkillService skillService)
    {
        this.skillService = skillService;
    }

    @Inject
    public TutorialCreate(
            ModelFunctionFactory factory
    ) {
        this.factory = factory;
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
        // Prevent saving without choosing a Skill (foreign keys should be set)
        if(tutorial.getSkill() == null)
            return null;
        tutorialService.create(factory.modelToTutorial().apply(tutorial));
        //return "/skill/skill_list.xhtml?faces-redirect=true";
        return "/skill/skill_view?id="+tutorial.getSkill().getId()+"&faces-redirect=true";
    }

}
