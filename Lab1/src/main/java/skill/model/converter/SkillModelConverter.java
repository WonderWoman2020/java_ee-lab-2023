package skill.model.converter;

import component.ModelFunctionFactory;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import skill.entity.Skill;
import skill.model.SkillModel;
import skill.service.SkillService;


import java.util.Optional;
import java.util.UUID;

/**
 * Faces converter for {@link SkillModel}. The managed attribute in {@link @FacesConverter} allows the converter to
 * be the CDI bean. In previous version of JSF converters were always created inside JSF lifecycle and where not managed
 * by container that is injection was not possible. As this bean is not annotated with scope the beans.xml descriptor
 * must be present.
 */
@FacesConverter(forClass = SkillModel.class, managed = true)
public class SkillModelConverter implements Converter<SkillModel> {

    /**
     * Service for skills management.
     */
    private final SkillService service;

    /**
     * Factory producing functions for conversion between models and entities.
     */
    private final ModelFunctionFactory factory;


    /**
     * @param service service for skills management
     * @param factory factory producing functions for conversion between models and entities
     */
    @Inject
    public SkillModelConverter(SkillService service, ModelFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    @Override
    public SkillModel getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        Optional<Skill> skill = service.find(UUID.fromString(value));
        return skill.map(factory.skillToModel()).orElse(null);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, SkillModel value) {
        return value == null ? "" : value.getId().toString();
    }

}
