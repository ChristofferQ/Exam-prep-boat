package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.BoatDTO;
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

    //RolesAllowed not added for easier testing
    @Path("harbour/{id}")
    //@RolesAllowed("user")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getBoatsByHarbour(@PathParam("id") long id) {
        return Response.ok(GSON.toJson(FACADE.getBoatsByHarbour(id))).build();
    }

    //RolesAllowed not added for easier testing
    @Path("boatowner/{id}")
    //@RolesAllowed("user")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getOwnerByBoat(@PathParam("id") long id) {
        return Response.ok(GSON.toJson(FACADE.getOwnerByBoat(id))).build();
    }

    //RolesAllowed not added for easier testing
    @Path("createboat")
    //@RolesAllowed("admin")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createBoat(String Boat){
        BoatDTO b = GSON.fromJson(Boat, BoatDTO.class);
        BoatDTO bo = FACADE.createBoat(b);
        return Response.ok(bo).build();
    }

    //RolesAllowed not added for easier testing
    @Path("connectboat/{id}")
    //@RolesAllowed("admin")
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response connectBoatWithHarbour(@PathParam("id") long id, String boat){
        BoatDTO b = GSON.fromJson(boat, BoatDTO.class);
        b.setId(id);
        BoatDTO bEdited = FACADE.connectBoatWithHarbour(b);
        return Response.ok(GSON.toJson(bEdited)).build();
    }

    

}
