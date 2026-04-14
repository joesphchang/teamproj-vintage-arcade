package com.vintagearcade.rest;

import com.vintagearcade.entity.Cabinet;
import com.vintagearcade.persistence.GenericDao;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * The type Cabinet resource.
 */
@Path("/cabinets")
@Produces(MediaType.APPLICATION_JSON)
public class CabinetResource {

    private final GenericDao<Cabinet> dao = new GenericDao<>(Cabinet.class);

    /**
     * Gets all cabinets.
     *
     * @return the all cabinets
     */
    @GET
    public Response getAllCabinets() {
        List<Cabinet> cabinets = dao.getAll();
        return Response.status(200).entity(cabinets).build();
    }

    /**
     * Gets cabinet by id.
     *
     * @param id the id
     * @return the cabinet by id
     */
    @GET
    @Path("/{id}")
    public Response getCabinetById(@PathParam("id") int id) {
        Cabinet cabinet = dao.getById(id);
        if (cabinet == null) {
            return Response.status(404).entity("Cabinet not found").build();
        }
        return Response.status(200).entity(cabinet).build();
    }

    /**
     * Add cabinet response.
     *
     * @param cabinet the cabinet
     * @return the response
     */
    @POST
    @Consumes("application/json")
    public Response addCabinet(Cabinet cabinet) {
        dao.insert(cabinet);
        return Response.status(201).entity("Cabinet added successfully").build();
    }

    /**
     * Update cabinet response.
     *
     * @param id      the id
     * @param cabinet the cabinet
     * @return the response
     */
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

    /**
     * Delete cabinet response.
     *
     * @param id the id
     * @return the response
     */
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

    /**
     * Search cabinets response.
     *
     * @param year         the year
     * @param manufacturer the manufacturer
     * @param condition    the condition
     * @return the response
     */
    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchCabinets(
            @QueryParam("year") Integer year,
            @QueryParam("manufacturer") String manufacturer,
            @QueryParam("condition") String condition) {

        if (condition != null && year != null) {
            return Response.status(200).entity(dao.getByNestedPropertyAndYear("condition", "status", condition, year)).build();
        }
        if (manufacturer != null && year != null) {
            return Response.status(200).entity(dao.getByNestedPropertyAndYear("manufacturer", "name", manufacturer, year)).build();
        }
        if (manufacturer != null) {
            return Response.status(200).entity(dao.getByNestedPropertyEqual("manufacturer", "name", manufacturer)).build();
        }
        if (condition != null) {
            return Response.status(200).entity(dao.getByNestedPropertyEqual("condition", "status", condition)).build();
        }
        if (year != null) {
            return Response.status(200).entity(dao.getByPropertyEqual("year", year)).build();
        }
        return Response.status(200).entity(dao.getAll()).build();
    }
}