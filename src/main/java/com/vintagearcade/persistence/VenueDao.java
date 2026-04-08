package com.vintagearcade.persistence;

import com.vintagearcade.entity.Cabinet;
import com.vintagearcade.entity.Venue;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import static com.vintagearcade.persistence.SessionFactoryProvider.getSessionFactory;

/**
 * The type Venue dao.
 */
public class VenueDao extends GenericDao<Venue> {

    /**
     * Instantiates a new Venue dao.
     */
    public VenueDao() {
        super(Venue.class);
    }

    /**
     * Delete venue.
     *
     * @param venueId the venue id
     */
    public void deleteVenue(int venueId) {
        Session session = getSessionFactory().openSession(); // use your session factory
        Transaction transaction = session.beginTransaction();

        // Load the venue with cabinets initialized
        Venue venue = session.get(Venue.class, venueId);
        if (venue != null) {
            // Force initialize the cabinets collection
            Hibernate.initialize(venue.getCabinets());

            // Remove venue from each cabinet's venue set
            for (Cabinet cabinet : venue.getCabinets()) {
                cabinet.getVenues().remove(venue);
            }
            // Clear the venue's cabinet set
            venue.getCabinets().clear();

            // Now delete the venue
            session.delete(venue);
        }

        transaction.commit();
        session.close();
    }

    /**
     * Gets by id with cabinets.
     *
     * @param id the id
     * @return the by id with cabinets
     */
    public Venue getByIdWithCabinets(int id) {
        Session session = getSessionFactory().openSession();
        try {
            Venue venue = session.get(Venue.class, id);
            if (venue != null) {
                Hibernate.initialize(venue.getCabinets());
            }
            return venue;
        } finally {
            session.close();
        }
    }
}