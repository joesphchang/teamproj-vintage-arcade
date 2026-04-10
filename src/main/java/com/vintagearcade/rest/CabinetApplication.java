package com.vintagearcade.rest;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Cabinet application.
 */
public class CabinetApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add(CabinetResouce.class);
        return h;
    }
}
