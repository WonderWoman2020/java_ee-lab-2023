package tutorial.view;

import component.ModelFunctionFactory;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import skill.entity.Skill;
import skill.model.SkillsModel;
import tutorial.entity.Tutorial;
import tutorial.model.TutorialsModel;
import tutorial.service.TutorialService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


/**
 * View bean for rendering list of characters.
 */
@RequestScoped
@Named
public class TutorialList {

    /**
     * Service for managing characters.
     */
    private final TutorialService service;

    /**
     * Characters list exposed to the view.
     */
    private TutorialsModel tutorials;

    /**
     * Factory producing functions for conversion between models and entities.
     */
    private final ModelFunctionFactory factory;

    @Getter
    @Setter
    private UUID id;

    /**
     * @param service character service
     * @param factory factory producing functions for conversion between models and entities
     */
    @Inject
    public TutorialList(TutorialService service, ModelFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    /**
     * In order to prevent calling service on different steps of JSF request lifecycle, model property is cached using
     * lazy getter.
     *
     * @return all characters
     */
    /*public TutorialsModel getTutorials() {
        if (tutorials == null) {
            tutorials = factory.tutorialsToModel().apply(service.findAll());
        }
        return tutorials;
    }*/

    public TutorialsModel getTutorials() {
        if (tutorials == null) {
            tutorials = factory.tutorialsToModel().apply(service.findAllBySkill(id).orElseThrow());
        }
        return tutorials;
    }

    /*public void init() throws IOException {
        Optional<List<Tutorial>> tutorials = service.findAllBySkill(id);
        if (tutorials.isPresent()) {
            this.tutorials = factory.tutorialsToModel().apply(tutorials.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Tutorials not found");
        }
    }*/

    /**
     * Action for clicking delete action.
     *
     * @param tutorial character to be removed
     * @return navigation case to list_characters
     */
    public String deleteAction(TutorialsModel.Tutorial tutorial) {
        service.delete(tutorial.getId());
        return "skill_view?faces-redirect=true";
    }

}
