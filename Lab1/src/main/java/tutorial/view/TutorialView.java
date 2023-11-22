package tutorial.view;

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
import skill.service.SkillService;
import tutorial.entity.Tutorial;
import tutorial.model.TutorialModel;
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
public class TutorialView implements Serializable {

    /**
     * Service for managing tutorials
     */
    private TutorialService service;

    /**
     * Factory producing functions for conversion between models and entities.
     */
    private final ModelFunctionFactory factory;

    /**
     * Tutorial id.
     */
    @Setter
    @Getter
    private UUID id;

    /**
     * Tutorial exposed to the view.
     */
    @Getter
    private TutorialModel tutorial;

    @Setter
    @Getter
    private UUID skillId;

    @EJB
    public void setService(TutorialService tutorialService)
    {
        this.service = tutorialService;
    }

    /**
     * @param service service for managing skills
     * @param factory factory producing functions for conversion between models and entities
     */
    @Inject
    public TutorialView(ModelFunctionFactory factory) {
        this.factory = factory;
    }

    /**
     * In order to prevent calling service on different steps of JSF request lifecycle, model property is cached within
     * field and initialized during init of the view.
     */
    public void init() throws IOException {
        Optional<Tutorial> tutorial = service.find(id);
        if (tutorial.isPresent()) {
            if(tutorial.get().getSkill() != null)
                skillId = tutorial.get().getSkill().getId();
            //skillId = null;
            this.tutorial = factory.tutorialToModel().apply(tutorial.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Tutorial not found");
        }
    }

}
