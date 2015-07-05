package com.senacor.geodata.views;

import com.senacor.geodata.GeoDataUI;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by dschmitz on 14/06/15.
 */
//@Theme("valo")
public class LoginView extends VerticalLayout {
    public static final String VIEW_NAME = "";
    private final GeoDataUI geoDataUI;

    public LoginView(GeoDataUI geoDataUI) {
        this.geoDataUI = geoDataUI;
        setSizeFull();

        VerticalLayout layout = new VerticalLayout();
        layout.setSizeUndefined();
        layout.setSpacing(true);
        layout.addComponents(buildHeader(), buildLoginField());

        Component loginButton = buildLoginButton();
        layout.addComponent(loginButton);

        layout.setComponentAlignment(loginButton, Alignment.BOTTOM_RIGHT);
        addComponent(layout);

        setComponentAlignment(layout, Alignment.MIDDLE_CENTER);
    }

    private Component buildLoginButton() {
        Button login = new Button("Log into GeoData", (event) -> {
            geoDataUI.userLoggedIn();
        });

//        button.setIcon(VaadinIcons.VAADIN_V);
        login.addStyleName(ValoTheme.BUTTON_PRIMARY);
        login.addStyleName(ValoTheme.BUTTON_SMALL);
        login.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        login.focus();

        return login;
    }

    private Component buildLoginField() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSizeUndefined();
        layout.setSpacing(true);

        TextField loginName = new TextField("Login account name", "");
        loginName.setIcon(FontAwesome.USER);
        loginName.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        loginName.setRequired(true);
        layout.addComponent(loginName);

        PasswordField passwordField = new PasswordField("Password for logging in", "");
        passwordField.setIcon(FontAwesome.USER);
        passwordField.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        passwordField.setRequired(true);
        layout.addComponent(passwordField);

        return layout;
    }

    private Component buildHeader() {
        VerticalLayout layout = new VerticalLayout();

        Label header = new Label("Welcome to GeoData");
        header.setSizeUndefined();
        header.addStyleName(ValoTheme.LABEL_H3);
        header.addStyleName(ValoTheme.LABEL_COLORED);

        layout.addComponents(header, new Label("Please enter your credentials and log in"));
        return layout;
    }
}
