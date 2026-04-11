package com.vintagearcade.rest;

import com.vintagearcade.entity.Cabinet;
import com.vintagearcade.persistence.GenericDao;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/cabinets")
@Produces(MediaType.APPLICATION_JSON)
public class CabinetResource {

    private final GenericDao<Cabinet> dao = new GenericDao<>(Cabinet.class);

    @GET
    public Response getAllCabinets() {
        List<Cabinet> cabinets = dao.getAll();
        return Response.status(200).entity(cabinets).build();
    }

    @GET
    @Path("/{id}")
    public Response getCabinetById(@PathParam("id") int id) {
        Cabinet cabinet = dao.getById(id);
        if (cabinet == null) {
            return Response.status(404).entity("Cabinet not found").build();
        }
        return Response.status(200).entity(cabinet).build();
    }

    @POST
    @Consumes("application/json")
    public Response addCabinet(Cabinet cabinet) {
        dao.insert(cabinet);
        return Response.status(201).entity("Cabinet added successfully").build();
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    public Response updateCabinet(@PathParam("id") int id, Cabinet cabinet) {
        if (dao.getById(id) == null) {
            return Response.status(404).entity("Cabinet not found").build();
        }
        cabinet.setGameId(id);
        dao.update(cabinet);
        return Response.status(200).entity("Cabinet updated").build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCabinet(@PathParam("id") int id) {
        Cabinet cabinet = dao.getById(id);
        if (cabinet == null) {
            return Response.status(404).entity("Cabinet not found").build();
        }
        dao.delete(cabinet);
        return Response.status(200).entity("Cabinet removed").build();
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchCabinets(@QueryParam("year") Integer year) {
        if (year != null) {
            return Response.status(200).entity(dao.getByPropertyEqual("year", year)).build();
        }
        return Response.status(200).entity(dao.getAll()).build();
    }
}