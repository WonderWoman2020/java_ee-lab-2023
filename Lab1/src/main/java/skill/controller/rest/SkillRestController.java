package skill.controller.rest;

//import controller.exception.BadRequestException;
//import controller.exception.NotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.BadRequestException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import skill.controller.api.SkillController;
import skill.dto.GetSkillResponse;
import skill.dto.GetSkillsResponse;
import skill.dto.PatchSkillRequest;
import skill.dto.PutSkillRequest;
import skill.dto.function.RequestToSkillFunction;
import skill.dto.function.SkillToResponseFunction;
import skill.dto.function.SkillsToResponseFunction;
import skill.dto.function.UpdateSkillWithRequestFunction;
import skill.service.SkillService;

import java.util.UUID;

@Path("")//Annotation required by the specification.
public class SkillRestController implements SkillController {

    private SkillService service;

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
    public SkillRestController(SkillService service,
                               @SuppressWarnings("CdiInjectionPointsInspection") UriInfo uriInfo) {
        this.service = service;
        this.uriInfo = uriInfo;
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

            response.setHeader("Location", uriInfo.getBaseUriBuilder()
                    .path(SkillController.class, "getSkill")
                    .build(id)
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
    public void patchSkill(UUID id, PatchSkillRequest request) {
        service.find(id).ifPresentOrElse(
                entity -> service.update(new UpdateSkillWithRequestFunction().apply(entity, request)),
                () -> {
                    throw new NotFoundException();
                }
        );
    }
}
