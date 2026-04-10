package com.vintagearcade.rest;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Venue application.
 */
public class VenueApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add(VenueResource.class);
        return h;
    }
}
