package com.senacor.geodata.views;

import com.senacor.geodata.GeoDataUI;
import com.senacor.geodata.views.components.BasicPrimaryButton;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import javax.annotation.Nonnull;

/**
 * View for login.
 *
 * @author dschmitz
 */
//@Theme("valo")
public class LoginView extends VerticalLayout {
    private final GeoDataUI geoDataUI;

    public LoginView(@Nonnull GeoDataUI geoDataUI) {
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
        Button login = new BasicPrimaryButton("Log into GeoData", (event) -> geoDataUI.userLoggedIn());
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
