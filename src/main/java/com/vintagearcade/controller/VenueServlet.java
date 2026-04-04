package com.vintagearcade.controller;

import com.vintagearcade.entity.Venue;
import com.vintagearcade.persistence.VenueDao;
import com.vintagearcade.persistence.GenericDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

@WebServlet("/venues")
public class VenueServlet extends HttpServlet {

    private GenericDao<Venue, Integer> venueDao = new VenueDao();
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
        venueDao.create(venue);

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

        int id = Integer.parseInt(request.getParameter("id"));
        venueDao.delete(id);

        response.setStatus(HttpServletResponse.SC_NO_CONTENT);

    }


}
