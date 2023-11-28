package skill.view;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import skill.model.SkillsModel;
import skill.service.SkillService;
import component.ModelFunctionFactory;

import java.io.Serializable;


/**
 * View bean for rendering list of skills.
 */
//@RequestScoped
@ViewScoped
@Named
public class SkillList implements Serializable {

    /**
     * Service for managing skills.
     */
    private SkillService service;

    /**
     * Skills list exposed to the view.
     */
    private SkillsModel skills;

    /**
     * Factory producing functions for conversion between models and entities.
     */
    private final ModelFunctionFactory factory;

    /**
     * @param service skills service
     * @param factory factory producing functions for conversion between models and entities
     */

    @EJB
    public void setService(SkillService skillService)
    {
        this.service = skillService;
    }
    @Inject
    public SkillList(ModelFunctionFactory factory) {
        this.factory = factory;
    }

    /**
     * In order to prevent calling service on different steps of JSF request lifecycle, model property is cached using
     * lazy getter.
     *
     * @return all skills
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
     * @param skill to be removed
     * @return navigation case to skill_list
     */
    /*public String deleteAction(SkillsModel.Skill skill) {
        service.delete(skill.getId());
        return "skill_list?faces-redirect=true";
    }*/

    public void deleteAction(SkillsModel.Skill skill) {
        service.delete(skill.getId());
        this.skills = null;
    }


}
