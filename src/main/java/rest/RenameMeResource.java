package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import utils.EMF_Creator;
import facades.FacadeExample;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Todo Remove or change relevant parts before ACTUAL use
@Path("xxx")
public class RenameMeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final FacadeExample FACADE =  FacadeExample.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Path("owners")
    @RolesAllowed("user")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllOwners() {
        return Response.ok(GSON.toJson(FACADE.getAllOwners())).build();
    }

    @Path("harbour/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getBoatsByHarbour(@PathParam("id") long id) {
        return Response.ok(GSON.toJson(FACADE.getBoatsByHarbour(id))).build();
    }
}
