package com.senacor.geodata.views;

import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import javax.annotation.PostConstruct;

/**
 * Created by dschmitz on 14/06/15.
 */
@SpringView(name = LoginView.VIEW_NAME)
@Theme("valo")
public class LoginView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "";

    @PostConstruct
    protected void init() {
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
            UI.getCurrent().getNavigator().navigateTo(GeoDataPortalView.VIEW_NAME);
        });
//        login.setDescription("Click on this button to login");
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
//        loginName.setDescription("Enter your login name into this field");
        loginName.setIcon(FontAwesome.USER);
        loginName.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        loginName.setRequired(true);
        layout.addComponent(loginName);

        PasswordField passwordField = new PasswordField("Password for logging in", "");
//        passwordField.setDescription("Enter your password into this field");
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

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        // see init
    }
}
