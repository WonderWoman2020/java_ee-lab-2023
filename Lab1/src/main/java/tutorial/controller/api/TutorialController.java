package tutorial.controller.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import tutorial.dto.GetTutorialResponse;
import tutorial.dto.GetTutorialsResponse;

import java.util.UUID;

@Path("")
public interface TutorialController {
    @GET
    @Path("/tutorials")
    @Produces(MediaType.APPLICATION_JSON)
    GetTutorialsResponse getTutorials();
    @GET
    @Path("/tutorials/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    GetTutorialResponse getTutorial(@PathParam("id") UUID uuid);

    @DELETE
    @Path("/tutorials/{id}")
    void deleteTutorial(@PathParam("id") UUID id);

    /**
     * Hierarchical by skill requests
     */
    @GET
    @Path("/skills/{skillId}/tutorials")
    @Produces(MediaType.APPLICATION_JSON)
    GetTutorialsResponse getTutorialsBySkill(@PathParam("skillId") UUID skillId);

}
