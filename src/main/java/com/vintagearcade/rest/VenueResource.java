package com.vintagearcade.rest;

import com.vintagearcade.entity.Venue;
import com.vintagearcade.persistence.GenericDao;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * The type Venue resource.
 */
@Path("/venues")
@Produces(MediaType.APPLICATION_JSON)
public class VenueResource {

    private final GenericDao<Venue> dao = new GenericDao<>(Venue.class);

    /**
     * Gets all venues.
     *
     * @return the all venues
     */
    @GET
    public Response getAllVenues() {
        List<Venue> venues = dao.getAll();
        return Response.status(200).entity(venues).build();
    }

    /**
     * Gets venue by id.
     *
     * @param id the id
     * @return the venue by id
     */
    @GET
    @Path("/{id}")
    public Response getVenueById(@PathParam("id") int id) {
        Venue venue = dao.getById(id);
        if (venue == null) {
            return Response.status(404).entity("Venue not found").build();
        }
        // Relationship with cabinets is handled by Hibernate/Jackson automatically
        return Response.status(200).entity(venue).build();
    }

    /**
     * Add venue response.
     *
     * @param venue the venue
     * @return the response
     */
    @POST
    @Consumes("application/json")
    public Response addVenue(Venue venue) {
        dao.insert(venue);
        return Response.status(201).entity("Venue created").build();
    }

    /**
     * Update venue response.
     *
     * @param id    the id
     * @param venue the venue
     * @return the response
     */
    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    public Response updateVenue(@PathParam("id") int id, Venue venue) {
        if (dao.getById(id) == null) {
            return Response.status(404).entity("Venue not found").build();
        }
        venue.setVenueId(id);
        dao.update(venue);
        return Response.status(200).entity("Venue updated").build();
    }

    /**
     * Delete venue response.
     *
     * @param id the id
     * @return the response
     */
    @DELETE
    @Path("/{id}")
    public Response deleteVenue(@PathParam("id") int id) {
        Venue venue = dao.getById(id);
        if (venue == null) {
            return Response.status(404).entity("Venue not found").build();
        }
        dao.delete(venue);
        return Response.status(200).entity("Venue deleted").build();
    }

    /**
     * Search venues response.
     *
     * @param location the location
     * @return the response
     */
    @GET
    @Path("/search")
    public Response searchVenues(@QueryParam("location") String location) {
        if (location != null) {
            return Response.status(200).entity(dao.getByPropertyLike("location", location)).build();
        }
        return Response.status(200).entity(dao.getAll()).build();
    }
}