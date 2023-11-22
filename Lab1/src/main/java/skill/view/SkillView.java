package skill.view;

import component.ModelFunctionFactory;
import jakarta.ejb.EJB;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import skill.entity.Skill;
import skill.model.SkillModel;
import skill.service.SkillService;
import tutorial.model.TutorialsModel;
import tutorial.service.TutorialService;


import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

/**
 * View bean for rendering single skill information.
 */
@ViewScoped
@Named
public class SkillView implements Serializable {

    /**
     * Service for managing skills
     */
    private SkillService service;

    /**
     * Factory producing functions for conversion between models and entities.
     */
    private final ModelFunctionFactory factory;

    /**
     * Skill id.
     */
    @Setter
    @Getter
    private UUID id;

    /**
     * Skill exposed to the view.
     */
    @Getter
    private SkillModel skill;

    /**
     * Service for managing tutorials
     */
    private TutorialService tutorialService;

    /**
     * Tutorials list exposed to the view.
     */
    private TutorialsModel tutorials;

    @EJB
    public void setService(SkillService skillService)
    {
        this.service = skillService;
    }

    @EJB
    public void setTutorialService(TutorialService tutorialService)
    {
        this.tutorialService = tutorialService;
    }


    /**
     * @param service service for managing skills
     * @param factory factory producing functions for conversion between models and entities
     */
    @Inject
    public SkillView(ModelFunctionFactory factory) {
        this.factory = factory;
    }

    /**
     * In order to prevent calling service on different steps of JSF request lifecycle, model property is cached within
     * field and initialized during init of the view.
     */
    public void init() throws IOException {
        Optional<Skill> skill = service.find(id);
        if (skill.isPresent()) {
            this.skill = factory.skillToModel().apply(skill.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Skill not found");
        }
    }

    public TutorialsModel getTutorials() {
        if (tutorials == null) {
            tutorials = factory.tutorialsToModel().apply(tutorialService.findAllBySkill(id).orElseThrow());
        }
        return tutorials;
    }

    public String deleteAction(TutorialsModel.Tutorial tutorial) {
        tutorialService.delete(tutorial.getId());
        //String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        return "skill_view?id="+id+"&faces-redirect=true";
    }

}
