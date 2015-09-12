package com.senacor.geodata.views.session;

import com.senacor.geodata.model.User;
import com.vaadin.server.VaadinSession;

import javax.annotation.Nonnull;

/**
 * @author dschmitz
 */
public class GeoDataSession {


    public static void login(@Nonnull User user) {
        VaadinSession.getCurrent().setAttribute("user", user);
    }

    public static void logout() {

    }
}
