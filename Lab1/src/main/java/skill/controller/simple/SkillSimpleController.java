package skill.controller.simple;

import controller.exception.NotFoundException;
import controller.exception.BadRequestException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import skill.controller.api.SkillController;
import skill.dto.GetSkillResponse;
import skill.dto.GetSkillsResponse;
import skill.dto.PatchSkillRequest;
import skill.dto.PutSkillRequest;
import skill.dto.function.RequestToSkillFunction;
import skill.dto.function.SkillToResponseFunction;
import skill.dto.function.SkillsToResponseFunction;
import skill.service.SkillService;
import user.dto.function.UserToResponseFunction;

import java.util.UUID;

@RequestScoped
@Alternative
public class SkillSimpleController implements SkillController {

    private SkillService service;
    @Inject
    public SkillSimpleController(SkillService service) {
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

    @Override
    public void patchSkill(UUID id, PatchSkillRequest request) {

    }
}
