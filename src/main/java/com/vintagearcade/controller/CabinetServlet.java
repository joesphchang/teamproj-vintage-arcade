package com.vintagearcade.controller;

import com.vintagearcade.entity.Cabinet;
import com.vintagearcade.persistence.GenericDao;
import com.vintagearcade.error.ApiError;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.io.IOException;
import java.lang.IllegalAccessException;

@WebServlet("/cabinets")
public class CabinetServlet extends HttpServlet {

    private GenericDao<Cabinet> cabinetDao = new GenericDao<>(Cabinet.class);
    private ObjectMapper mapper = new ObjectMapper();

    // GET
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

    // POST
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

    // PUT
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

    // DELETE
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

    // Validation method
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
