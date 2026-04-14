package com.vintagearcade.controller;

import com.vintagearcade.entity.Cabinet;
import com.vintagearcade.persistence.GenericDao;
import com.vintagearcade.error.ApiError;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.io.IOException;
import java.lang.IllegalAccessException;

/**
 * Servlet responsible for handling HTTP requests related to {@link Cabinet} resources.
 *
 * <p>This servlet provides REST-like endpoints for managing arcade cabinets,
 * including retrieving, creating, updating, and deleting cabinet records.</p>
 *
 * <p>Supported operations:</p>
 * <ul>
 *     <li>GET - Retrieve one or all cabinets</li>
 *     <li>POST - Create a new cabinet</li>
 *     <li>PUT - Update an existing cabinet</li>
 *     <li>DELETE - Remove a cabinet</li>
 * </ul>
 *
 * <p>All responses are returned in JSON format.</p>
 */
@WebServlet("/cabinets")
public class CabinetServlet extends HttpServlet {

    private GenericDao<Cabinet> cabinetDao = new GenericDao<>(Cabinet.class);
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * Handles HTTP GET requests.
     *
     * <p>If an "id" parameter is provided, returns a single cabinet.
     * Otherwise, returns a list of all cabinets.</p>
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
                Cabinet cabinet = cabinetDao.getById(id);
                if (cabinet == null) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    mapper.writeValue(response.getWriter(), new ApiError(404, "Cabinet not found"));
                    return;
                }
                mapper.writeValue(response.getWriter(), cabinet);
                return;
            }

            // GET all
            List<Cabinet> cabinets = cabinetDao.getAll();
            mapper.writeValue(response.getWriter(), cabinets);
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            mapper.writeValue(response.getWriter(),
                    new ApiError(400, "Invalid ID format"));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            mapper.writeValue(response.getWriter(),
                    new ApiError(500, "Server error"));
        }
    }

    /**
     * Handles HTTP POST requests.
     *
     * <p>Creates a new {@link Cabinet} from the JSON request body.</p>
     *
     * @param request  the HTTP request containing cabinet data in JSON format
     * @param response the HTTP response used to return status and errors
     * @throws IOException if an input or output error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("application/json");

        try {
            Cabinet cabinet = mapper.readValue(request.getReader(), Cabinet.class);

            // validate first
            validateCabinet(cabinet);

            // then call DAO
            cabinetDao.insert(cabinet);

            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            mapper.writeValue(response.getWriter(),
                    new ApiError(400, e.getMessage()));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            mapper.writeValue(response.getWriter(),
                    new ApiError(500, "Server error"));
        }
    }

    /**
     * Handles HTTP PUT requests.
     *
     * <p>Updates an existing {@link Cabinet} using the provided ID and JSON body.</p>
     *
     * @param request  the HTTP request containing updated cabinet data
     * @param response the HTTP response used to return status and errors
     * @throws IOException if an input or output error occurs
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("application/json");

        try {
            String idParam = request.getParameter("id");
            if (idParam == null) {
                throw new IllegalArgumentException("Missing cabinet ID");
            }

            int id = Integer.parseInt(idParam);

            Cabinet cabinet = mapper.readValue(request.getReader(), Cabinet.class);
            cabinet.setGameId(id);

            validateCabinet(cabinet);

            cabinetDao.update(cabinet);

            response.setStatus(HttpServletResponse.SC_OK);

        } catch (IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            mapper.writeValue(response.getWriter(),
                    new ApiError(400, e.getMessage()));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            mapper.writeValue(response.getWriter(),
                    new ApiError(500, "Server error"));
        }

    }

    /**
     * Handles HTTP DELETE requests.
     *
     * <p>Deletes a {@link Cabinet} based on the provided ID.</p>
     *
     * @param request  the HTTP request containing the cabinet ID
     * @param response the HTTP response used to return status and errors
     * @throws IOException if an input or output error occurs
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("application/json");

        try {
            String idParam = request.getParameter("id");
            if (idParam == null) {
                throw new IllegalArgumentException("Missing cabinet ID");
            }

            int id = Integer.parseInt(idParam);

            Cabinet cabinet = cabinetDao.getById(id);

            if (cabinet == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                mapper.writeValue(response.getWriter(), new ApiError(404, "Cabinet not found"));
                return;
            }

            cabinetDao.delete(cabinet);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            mapper.writeValue(response.getWriter(), new ApiError(400, e.getMessage()));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            mapper.writeValue(response.getWriter(), new ApiError(500, "Server error"));
        }
    }

    /**
     * Validates the fields of a {@link Cabinet} object before persistence.
     *
     * <p>Validation rules include:</p>
     * <ul>
     *     <li>Game name must not be null or blank</li>
     *     <li>Year must be between 1970 and the current year</li>
     *     <li>Price per play must be greater than 0</li>
     *     <li>Manufacturer must be provided</li>
     *     <li>Condition must be provided</li>
     * </ul>
     *
     * @param cabinet the cabinet object to validate
     * @throws IllegalAccessException if validation fails
     */
    private void validateCabinet(Cabinet cabinet) throws IllegalAccessException {

        // current year variable for year check
        int currentYear = java.time.Year.now().getValue();

        // check if a game's name was input
        if (cabinet.getGameName() == null || cabinet.getGameName().isBlank()) {
            throw new IllegalAccessException("Game name is required");
        }

        // check if a game's year is within the valid range
        if (cabinet.getYear() < 1970 || cabinet.getYear() > currentYear) {
            throw new IllegalAccessException("Year must be between 1970 and current year");
        }

        // check if a game's price to play is valid (above 0)
        if (cabinet.getPricePerPlay() <= 0) {
            throw new IllegalAccessException("Price must be greater than 0");
        }

        // check if manufacturer is provided
        if (cabinet.getManufacturer() == null) {
            throw new IllegalAccessException("Manufacturer is required");
        }

        // check if condition is provided
        if (cabinet.getCondition() == null) {
            throw new IllegalAccessException("Condition is required");
        }
    }

}
