package skill.view;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import skill.model.SkillsModel;
import skill.service.SkillService;
import component.ModelFunctionFactory;


/**
 * View bean for rendering list of characters.
 */
@RequestScoped
@Named
public class SkillList {

    /**
     * Service for managing characters.
     */
    private final SkillService service;

    /**
     * Characters list exposed to the view.
     */
    private SkillsModel skills;

    /**
     * Factory producing functions for conversion between models and entities.
     */
    private final ModelFunctionFactory factory;

    /**
     * @param service character service
     * @param factory factory producing functions for conversion between models and entities
     */
    @Inject
    public SkillList(SkillService service, ModelFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    /**
     * In order to prevent calling service on different steps of JSF request lifecycle, model property is cached using
     * lazy getter.
     *
     * @return all characters
     */
    public SkillsModel getSkills() {
        if (skills == null) {
            skills = factory.skillsToModel().apply(service.findAll());
        }
        return skills;
    }

    /**
     * Action for clicking delete action.
     *
     * @param skill character to be removed
     * @return navigation case to list_characters
     */
    public String deleteAction(SkillsModel.Skill skill) {
        service.delete(skill.getId());
        return "skill_list?faces-redirect=true";
    }

}
