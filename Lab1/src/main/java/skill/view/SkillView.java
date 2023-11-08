package skill.view;

import component.ModelFunctionFactory;
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


import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

/**
 * View bean for rendering single character information.
 */
@ViewScoped
@Named
public class SkillView implements Serializable {

    /**
     * Service for managing characters.
     */
    private final SkillService service;

    /**
     * Factory producing functions for conversion between models and entities.
     */
    private final ModelFunctionFactory factory;

    /**
     * Character id.
     */
    @Setter
    @Getter
    private UUID id;

    /**
     * Character exposed to the view.
     */
    @Getter
    private SkillModel skill;


    /**
     * @param service service for managing characters
     * @param factory factory producing functions for conversion between models and entities
     */
    @Inject
    public SkillView(SkillService service, ModelFunctionFactory factory) {
        this.service = service;
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

}
