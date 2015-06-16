package com.senacor.geodata;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by dschmitz on 13/06/15.
 */
@Theme("valo")
@SpringUI  // (path = "/geodata")
public class GeoDataUI extends UI {


    private SpringViewProvider viewProvider;

    @Autowired
    public GeoDataUI(SpringViewProvider viewProvider) {
        super();
        this.viewProvider = viewProvider;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setSizeFull();

        Navigator navigator = new Navigator(this, this);
        navigator.addProvider(this.viewProvider);
    }
}
