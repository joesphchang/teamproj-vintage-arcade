package com.vintagearcade.persistence;

import com.vintagearcade.entity.Cabinet;
import com.vintagearcade.entity.Manufacturer;
import com.vintagearcade.entity.Venue;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;

import static com.vintagearcade.persistence.SessionFactoryProvider.getSessionFactory;

public class CabinetDao extends GenericDao<Cabinet> {

    public CabinetDao() {
        super(Cabinet.class);
    }

    /**
     * Update a cabinet, ensuring all assigned venues are valid.
     *
     * @param cabinet the cabinet to update
     * @throws IllegalArgumentException if any assigned venue does not exist
     */
    public boolean updateCabinet(Cabinet cabinet) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            //Validate Manufacturer
            if (cabinet.getManufacturer() == null ||
                    session.get(Manufacturer.class, cabinet.getManufacturer().getManufacturerId()) == null) {
                throw new IllegalArgumentException("Invalid manufacturer");
            }
            // Validate venues
            Set<Venue> validVenues = new HashSet<>();
            if (cabinet.getVenues() != null && !cabinet.getVenues().isEmpty()) {
                for (Venue v : cabinet.getVenues()) {
                    Venue existingVenue = session.get(Venue.class, v.getVenueId());
                    if (existingVenue == null) {
                        throw new IllegalArgumentException("Invalid venue: " + v.getName());
                    }
                    validVenues.add(existingVenue);
                }
            }

            // Assign only valid venues
            cabinet.setVenues(validVenues);

            // Merge the cabinet update
            session.merge(cabinet);
            transaction.commit();
            return true;
        } catch (IllegalArgumentException e) {
            transaction.rollback();
            throw e;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    /**
     * Remove a specific venue from all cabinets without deleting the cabinets themselves.
     *
     * @param venueId the venue to remove
     */
    public void removeVenueFromCabinets(int venueId) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Venue venue = session.get(Venue.class, venueId);
        if (venue != null) {
            // Initialize cabinets to avoid lazy loading issues
            Hibernate.initialize(venue.getCabinets());

            for (Cabinet cabinet : venue.getCabinets()) {
                cabinet.getVenues().remove(venue);
                session.merge(cabinet);
            }

            // Clear the venue's cabinet set
            venue.getCabinets().clear();
            session.merge(venue);
        }

        transaction.commit();
        session.close();
    }

    public Cabinet getByIdWithVenues(int id) {
        Session session = getSessionFactory().openSession();
        try {
            Cabinet cabinet = session.get(Cabinet.class, id);
            if (cabinet != null) {
                Hibernate.initialize(cabinet.getVenues());
            }
            return cabinet;
        } finally {
            session.close();
        }
    }

}