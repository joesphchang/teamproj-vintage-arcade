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

        String idParam = request.getParameter("id");

        // GET by id
        if (idParam != null) {
            Cabinet cabinet = cabinetDao.getById(Integer.parseInt(idParam));
            mapper.writeValue(response.getWriter(), cabinet);
            return;
        }

        // GET all
        List<Cabinet> cabinets = cabinetDao.getAll();
        mapper.writeValue(response.getWriter(), cabinets);
    }

    // POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("application/json");

        try {
            Cabinet cabinet = mapper.readValue(request.getReader(), Cabinet.class);

            validateCabinet(cabinet);

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

        int id = Integer.parseInt(request.getParameter("id"));

        Cabinet cabinet = mapper.readValue(request.getReader(), Cabinet.class);
        cabinet.setGameId(id);

        cabinetDao.update(cabinet);

        response.setStatus(HttpServletResponse.SC_OK);
    }

    // DELETE
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Cabinet cabinet = mapper.readValue(request.getReader(), Cabinet.class);

        cabinetDao.delete(cabinet);

        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    private void validateCabinet(Cabinet cabinet) throws IllegalAccessException {

        if (cabinet.getGameName() == null || cabinet.getGameName().isBlank()) {
            throw new IllegalAccessException("Game name is required");
        }
    }

}
