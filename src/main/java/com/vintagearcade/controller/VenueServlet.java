package com.vintagearcade.controller;

import com.vintagearcade.entity.Cabinet;
import com.vintagearcade.entity.Venue;
import com.vintagearcade.persistence.GenericDao;
import com.vintagearcade.error.ApiError;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * Servlet responsible for handling HTTP requests related to {@link Venue} resources.
 *
 * <p>This servlet provides REST-like operations for managing arcade venues,
 * including retrieving, creating, updating, and deleting venues.</p>
 *
 * <p>Each venue may contain multiple {@link Cabinet} objects.</p>
 *
 * <p>Supported operations:</p>
 * <ul>
 *     <li>GET - Retrieve one or all venues</li>
 *     <li>POST - Create a new venue</li>
 *     <li>PUT - Update an existing venue</li>
 *     <li>DELETE - Remove a venue</li>
 * </ul>
 *
 * <p>All responses are returned in JSON format.</p>
 */
@WebServlet("/venues")
public class VenueServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(VenueServlet.class);

    private GenericDao<Venue> venueDao = new GenericDao<>(Venue.class);
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * Handles HTTP GET requests.
     *
     * <p>If an "id" parameter is provided, returns a specific venue.
     * Otherwise, returns a list of all venues.</p>
     *
     * @param request  the {@link HttpServletRequest} containing client request data
     * @param response the {@link HttpServletResponse} used to return JSON output
     * @throws IOException if an input or output error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("application/json");

        try {
            String idParam = request.getParameter("id");

            if (idParam != null) {
                int id = Integer.parseInt(idParam);
                Venue venue = venueDao.getById(id);
                if (venue == null) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    mapper.writeValue(response.getWriter(), new ApiError(404, "Venue not found"));
                    return;
                }
                mapper.writeValue(response.getWriter(), venue);
            } else {
                List<Venue> venues = venueDao.getAll();
                mapper.writeValue(response.getWriter(), venues);
            }
        } catch (NumberFormatException e) {
            logger.warn("Invalid ID format: " + request.getParameter("id"), e);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            mapper.writeValue(response.getWriter(), new ApiError(400, "Invalid venue ID format"));
        } catch (Exception e) {
            logger.error("Unexpected error in GET /venues", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            mapper.writeValue(response.getWriter(), new ApiError(500, "Server error"));
        }

    }

    /**
     * Handles HTTP POST requests.
     *
     * <p>Creates a new {@link Venue} from the JSON request body.</p>
     *
     * @param request  the HTTP request containing venue data in JSON format
     * @param response the HTTP response used to return status and errors
     * @throws IOException if an input or output error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            Venue venue = mapper.readValue(request.getReader(), Venue.class);
            validateVenue(venue);

            venueDao.insert(venue);

            response.setStatus(HttpServletResponse.SC_CREATED);
            mapper.writeValue(response.getWriter(), venue);
        } catch (IllegalArgumentException e) {
            logger.warn("Validation failed for new venue", e);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            mapper.writeValue(response.getWriter(), new ApiError(400, e.getMessage()));
        } catch (Exception e) {
            logger.error("Unexpected error in POST /venues", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            mapper.writeValue(response.getWriter(), new ApiError(500, "Server error"));
        }
    }

    /**
     * Handles HTTP PUT requests.
     *
     * <p>Updates an existing {@link Venue} using the provided ID and JSON body.</p>
     *
     * @param request  the HTTP request containing updated venue data
     * @param response the HTTP response used to return status and errors
     * @throws IOException if an input or output error occurs
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Venue existingVenue = venueDao.getById(id);

            if (existingVenue == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                mapper.writeValue(response.getWriter(), new ApiError(404, "Venue not found"));
                return;
            }

            Venue venue = mapper.readValue(request.getReader(), Venue.class);
            venue.setVenueId(id);
            validateVenue(venue);

            venueDao.update(venue);

            response.setStatus(HttpServletResponse.SC_OK);
            mapper.writeValue(response.getWriter(), venue);
        } catch (NumberFormatException e) {
            logger.warn("Invalid ID format: " + request.getParameter("id"), e);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            mapper.writeValue(response.getWriter(), new ApiError(400, "Invalid venue ID format"));
        } catch (IllegalArgumentException e) {
            logger.warn("Validation failed for updated venue", e);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            mapper.writeValue(response.getWriter(), new ApiError(400, e.getMessage()));
        } catch (Exception e) {
            logger.error("Unexpected error in PUT /venues", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            mapper.writeValue(response.getWriter(), new ApiError(500, "Server error"));
        }

    }

    /**
     * Handles HTTP DELETE requests.
     *
     * <p>Deletes a {@link Venue} based on the provided ID.</p>
     *
     * @param request  the HTTP request containing the venue ID
     * @param response the HTTP response used to return status and errors
     * @throws IOException if an input or output error occurs
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Venue venue = venueDao.getById(id);

            if (venue == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                mapper.writeValue(response.getWriter(), new ApiError(404, "Venue not found"));
                return;
            }

            venueDao.delete(venue);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (NumberFormatException e) {
            logger.warn("Invalid ID format: " + request.getParameter("id"), e);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            mapper.writeValue(response.getWriter(), new ApiError(400, "Invalid  venue ID format"));
        } catch (Exception e) {
            logger.error("Unexpected error in DELETE /venues", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            mapper.writeValue(response.getWriter(), new ApiError(500, "Server error"));
        }

    }

    /**
     * Validates the fields of a {@link Venue} object before persistence.
     *
     * <p>Validation rules include:</p>
     * <ul>
     *     <li>Venue name must not be null or blank</li>
     *     <li>Location must not be null or blank</li>
     *     <li>Opening time must be provided</li>
     *     <li>Closing time must be provided</li>
     *     <li>Opening time must be before closing time</li>
     * </ul>
     *
     * @param venue the venue object to validate
     * @throws IllegalAccessException if validation fails
     */
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
