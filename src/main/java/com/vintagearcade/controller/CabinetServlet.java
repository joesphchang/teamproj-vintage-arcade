package com.vintagearcade.controller;

import com.vintagearcade.entity.Cabinet;
import com.vintagearcade.persistence.CabinetDao;
import com.vintagearcade.persistence.GenericDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.io.IOException;

@WebServlet("/cabinets")
public class CabinetServlet extends HttpServlet {

    private GenericDao<Cabinet, Integer> cabinetDao = new CabinetDao();
    private ObjectMapper mapper = new ObjectMapper();

    // GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("application/json");

        String idParam = request.getParameter("id");
        String manufacturer = request.getParameter("manufacturer");
        String condition = request.getParameter("condition");
        String yearParam = request.getParameter("year");
        String venueIdParam = request.getParameter("venueId");

        // GET by id
        if (idParam != null) {
            Cabinet cabinet = cabinetDao.getById(Integer.parseInt(idParam));
            mapper.writeValue(response.getWriter(), cabinet);
            return;
        }

        if (venueIdParam != null) {
            int venueId = Integer.parseInt(venueIdParam);
            List<Cabinet> cabinets = ((CabinetDao) cabinetDao).getByVenue(venueId);
            mapper.writeValue(response.getWriter(), cabinets);
            return;
        }

        // Filtering
        if (manufacturer != null || condition != null || yearParam != null) {
            Integer year = (yearParam != null) ? Integer.parseInt(yearParam) : null;

            List<Cabinet> filtered = cabinetDao.filter(manufacturer, condition, year);

            mapper.writeValue(response.getWriter(), filtered);
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

        Cabinet cabinet = mapper.readValue(request.getReader(), Cabinet.class);

        int venueId = Integer.parseInt(request.getParameter("venueId"));

        ((CabinetDao) cabinetDao).create(cabinet, venueId);

        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    // PUT
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        int venueId = Integer.parseInt(request.getParameter("venueId"));

        Cabinet cabinet = mapper.readValue(request.getReader(), Cabinet.class);
        cabinet.setGameId(id);

        ((CabinetDao) cabinetDao).update(cabinet, venueId);

        response.setStatus(HttpServletResponse.SC_OK);
    }

    // DELETE
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        cabinetDao.delete(id);

        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

}
