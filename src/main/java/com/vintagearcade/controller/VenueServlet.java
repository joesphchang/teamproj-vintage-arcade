package com.vintagearcade.controller;

import com.vintagearcade.entity.Cabinet;
import com.vintagearcade.entity.Venue;
import com.vintagearcade.persistence.GenericDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalTime;

@WebServlet("/venues")
public class VenueServlet extends HttpServlet {

    private GenericDao<Venue> venueDao = new GenericDao<>(Venue.class);
    private ObjectMapper mapper = new ObjectMapper();

    // GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("application/json");

        String idParam = request.getParameter("id");

        if (idParam != null) {
            Venue venue = venueDao.getById(Integer.parseInt(idParam));
            mapper.writeValue(response.getWriter(), venue);
        } else {
            mapper.writeValue(response.getWriter(), venueDao.getAll());
        }

    }

    // POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Venue venue = mapper.readValue(request.getReader(), Venue.class);
        venueDao.insert(venue);

        response.setStatus(HttpServletResponse.SC_CREATED);

    }

    // PUT
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Venue venue = mapper.readValue(request.getReader(), Venue.class);
        venue.setVenueId(id);

        venueDao.update(venue);

        response.setStatus(HttpServletResponse.SC_OK);

    }

    // DELETE
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Venue venue = mapper.readValue(request.getReader(), Venue.class);
        venueDao.delete(venue);

        response.setStatus(HttpServletResponse.SC_NO_CONTENT);

    }

    // Validation method
    private void validateVenue(Venue venue) throws IllegalAccessException {

        // check if a venue's name was input
        if (venue.getName() == null || venue.getName().isBlank()) {
            throw new IllegalAccessException("Venue name is required");
        }

        // check if a venue's location was input
        if (venue.getLocation() == null|| venue.getLocation().isBlank()) {
            throw new IllegalAccessException("Venue location is required");
        }

        // check if a game's price to play is valid (above 0)
        if (venue.getOpenFrom() == null) {
            throw new IllegalAccessException("Opening time is required");
        }

        // check if manufacturer is provided
        if (venue.getOpenTo() == null) {
            throw new IllegalAccessException("Closing time is required");
        }

        // check if condition is provided
        if (!venue.getOpenFrom().isBefore(venue.getOpenTo())) {
            throw new IllegalAccessException("Opening time must be before closing time");
        }
    }

}
