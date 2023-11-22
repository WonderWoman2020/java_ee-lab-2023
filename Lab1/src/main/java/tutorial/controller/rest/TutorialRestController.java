package tutorial.controller.rest;

//import controller.exception.NotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.enterprise.inject.Alternative;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import tutorial.controller.api.TutorialController;
import tutorial.dto.GetTutorialResponse;
import tutorial.dto.GetTutorialsResponse;
import tutorial.dto.PatchTutorialRequest;
import tutorial.dto.PutTutorialRequest;
import tutorial.dto.function.RequestToTutorialFunction;
import tutorial.dto.function.TutorialToResponseFunction;
import tutorial.dto.function.TutorialsToResponseFunction;
import tutorial.dto.function.UpdateTutorialWithRequestFunction;
import tutorial.service.TutorialService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Path("")//Annotation required by the specification.
public class TutorialRestController implements TutorialController {

    private TutorialService service;

    /**
     * Allows to create {@link UriBuilder} based on current request.
     */
    private final UriInfo uriInfo;

    /**
     * Current HTTP Servlet response.
     */
    private HttpServletResponse response;

    @Context
    public void setResponse(HttpServletResponse response) {
        //ATM in this implementation only HttpServletRequest can be injected with CDI so JAX-RS injection is used.
        this.response = response;
    }

    @Inject
    public TutorialRestController(TutorialService service,
                                  @SuppressWarnings("CdiInjectionPointsInspection") UriInfo uriInfo)
    {
        this.service = service;
        this.uriInfo = uriInfo;
    }

    @Override
    public GetTutorialsResponse getTutorials() {
        return new TutorialsToResponseFunction().apply(service.findAll());
    }

    @Override
    public GetTutorialResponse getTutorial(UUID uuid) {
        return service.find(uuid).map(new TutorialToResponseFunction())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void deleteTutorial(UUID id) {
        service.find(id).ifPresentOrElse(
                entity -> service.delete(entity.getId()),
                () -> {
                    throw new NotFoundException();
                }
        );
    }

    @Override
    public GetTutorialsResponse getTutorialsBySkill(UUID skillId) {
        return new TutorialsToResponseFunction().apply(service.findAllBySkill(skillId)
                .orElseThrow(NotFoundException::new));
    }

    @Override
    public GetTutorialResponse getTutorialBySkill(UUID skillId, UUID tutorialId) {
        return new TutorialToResponseFunction().apply(service.findBySkillAndId(skillId, tutorialId)
                .orElseThrow(NotFoundException::new));
    }

    @Override
    public void deleteTutorialBySkill(UUID skillId, UUID tutorialId) {
        service.findBySkillAndId(skillId, tutorialId).ifPresentOrElse(
                entity -> service.delete(entity.getId()),
                () -> {
                    throw new NotFoundException();
                }
        );
    }

    @Override
    public void putTutorialBySkill(UUID skillId, UUID tutorialId, PutTutorialRequest request) {
        try {
            Map<String, UUID> uuids = new HashMap<>();
            uuids.put("tutorialId", tutorialId);
            uuids.put("skillId", skillId);
            service.create(new RequestToTutorialFunction().apply(uuids, request));

            response.setHeader("Location", uriInfo.getBaseUriBuilder()
                    .path(TutorialController.class, "getTutorialBySkill")
                    .build(skillId, tutorialId)
                    .toString());
            //This can be done with Response builder but requires method different return type.
            //Calling HttpServletResponse#setStatus(int) is ignored.
            //Calling HttpServletResponse#sendError(int) causes response headers and body looking like error.
            throw new WebApplicationException(Response.Status.CREATED);

        } catch (IllegalArgumentException ex) {
            throw new BadRequestException(ex);
        }
    }

    @Override
    public void patchTutorialBySkill(UUID skillId, UUID tutorialId, PatchTutorialRequest request) {
        service.findBySkillAndId(skillId, tutorialId).ifPresentOrElse(
                entity -> service.update(new UpdateTutorialWithRequestFunction().apply(entity, request)),
                () -> {
                    throw new NotFoundException();
                }
        );
    }
}
