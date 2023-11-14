package skill.controller.rest;

//import controller.exception.BadRequestException;
//import controller.exception.NotFoundException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.BadRequestException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import skill.controller.api.SkillController;
import skill.dto.GetSkillResponse;
import skill.dto.GetSkillsResponse;
import skill.dto.PutSkillRequest;
import skill.dto.function.RequestToSkillFunction;
import skill.dto.function.SkillToResponseFunction;
import skill.dto.function.SkillsToResponseFunction;
import skill.service.SkillService;

import java.util.UUID;

@Path("")//Annotation required by the specification.
public class SkillRestController implements SkillController {

    private SkillService service;
    @Inject
    public SkillRestController(SkillService service) {
        this.service = service;
    }

    @Override
    public GetSkillsResponse getSkills() {
        return new SkillsToResponseFunction().apply(service.findAll());
    }

    @Override
    public GetSkillResponse getSkill(UUID uuid) {
        return service.find(uuid).map(new SkillToResponseFunction())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void deleteSkill(UUID id) {
        service.find(id).ifPresentOrElse(
                entity -> service.delete(entity.getId()),
                () -> {
                    throw new NotFoundException();
                }
        );
    }

    @Override
    public void putSkill(UUID id, PutSkillRequest request) {
        try {
            service.create(new RequestToSkillFunction().apply(id, request));
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException(ex);
        }
    }
}
