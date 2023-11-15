package skill.controller.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import skill.dto.GetSkillResponse;
import skill.dto.GetSkillsResponse;
import skill.dto.PatchSkillRequest;
import skill.dto.PutSkillRequest;
import tutorial.dto.PatchTutorialRequest;

import java.util.UUID;

@Path("")
public interface SkillController {
    @GET
    @Path("/skills")
    @Produces(MediaType.APPLICATION_JSON)
    GetSkillsResponse getSkills();

    @GET
    @Path("/skills/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    GetSkillResponse getSkill(@PathParam("id") UUID uuid);

    @DELETE
    @Path("/skills/{id}")
    void deleteSkill(@PathParam("id") UUID id);

    @PUT
    @Path("/skills/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void putSkill(@PathParam("id") UUID id, PutSkillRequest request);

    @PATCH
    @Path("/skills/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void patchSkill(@PathParam("id") UUID id, PatchSkillRequest request);
}
