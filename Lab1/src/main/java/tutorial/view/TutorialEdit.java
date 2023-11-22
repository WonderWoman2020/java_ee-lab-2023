package tutorial.view;

import component.ModelFunctionFactory;
import jakarta.ejb.EJB;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import tutorial.entity.Tutorial;
import tutorial.model.TutorialEditModel;
import tutorial.service.TutorialService;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;


/**
 * View bean for rendering single character create form.
 */
@ViewScoped
@Named
@Log
@NoArgsConstructor(force = true)
public class TutorialEdit implements Serializable {

    /**
     * Service for managing characters.
     */
    private TutorialService service;

    /**
     * Factory producing functions for conversion between models and entities.
     */
    private final ModelFunctionFactory factory;

    /**
     * Tutorial id
     */

    @Setter
    @Getter
    private UUID id;

    /**
     * Tutorial exposed to the view.
     */
    @Getter
    private TutorialEditModel tutorial;

    @EJB
    public void setService(TutorialService tutorialService)
    {
        this.service = tutorialService;
    }

    @Inject
    public TutorialEdit(
            ModelFunctionFactory factory
    ) {
        this.factory = factory;
    }

    /**
     * In order to prevent calling service on different steps of JSF request lifecycle, model property is cached within
     * field and initialized during init of the view. @PostConstruct method is called after h:form header is already
     * rendered. Conversation should be started in f:metadata/f:event.
     */
    public void init() throws IOException {
        Optional<Tutorial> tutorial = service.find(id);
        if (tutorial.isPresent()) {
            this.tutorial = factory.tutorialToEditModel().apply(tutorial.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Tutorial not found");
        }
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
        service.update(factory.updateTutorial().apply(service.find(id).orElseThrow(), tutorial));
        //return "/skill/skill_list.xhtml?faces-redirect=true";
        return "tutorial_view?id="+id+"&faces-redirect=true";
    }

}
