package com.senacor.geodata.views;

import com.senacor.geodata.GeoDataUI;
import com.senacor.geodata.model.User;
import com.senacor.geodata.repository.UserRepository;
import com.senacor.geodata.views.components.BasicPrimaryButton;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;

import static com.vaadin.ui.Notification.Type.WARNING_MESSAGE;
import static com.vaadin.ui.Notification.show;

/**
 * View for login.
 *
 * @author dschmitz
 */
//@Theme("valo")
public class LoginView extends VerticalLayout {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginView.class);

    private final GeoDataUI geoDataUI;
    private final UserRepository userRepository;
    private final User user;

    @PropertyId("id")
    private TextField loginName;

    @PropertyId("email")
    private PasswordField passwordField;
    private final BeanFieldGroup binder;

    public LoginView(@Nonnull GeoDataUI geoDataUI, UserRepository userRepository) {
        this.geoDataUI = geoDataUI;
        this.userRepository = userRepository;
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

        user = userRepository.findAll().get(0);
        binder = new BeanFieldGroup<>(User.class);
        binder.setItemDataSource(new BeanItem<>(user));
        binder.bindMemberFields(this);
    }

    private Component buildLoginButton() {
        Button login = new BasicPrimaryButton("Log into GeoData", (event) -> {
            try {
                binder.commit();

                User loadedUser = userRepository.findOne(user.getId());
                if (null != loadedUser && user.getEmail().equals(loadedUser.getEmail())) {
                    LOGGER.info("Logging in {}", user.getId());
                    geoDataUI.userLoggedIn();
                } else {
                    LOGGER.warn("Login failed for {}", user.getId());
                    show("Login failed", "Cannot login. Username and password do not match.", WARNING_MESSAGE);
                }
            } catch (FieldGroup.CommitException e) {
                show("Login failed", "Cannot login.", WARNING_MESSAGE);
            }
        });
        login.setDisableOnClick(true);
        login.focus();

        return login;
    }

    private Component buildLoginField() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSizeUndefined();
        layout.setSpacing(true);

        loginName = new TextField("Login account name");
        loginName.setIcon(FontAwesome.USER);
        loginName.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        loginName.setRequired(true);
        layout.addComponent(loginName);

        passwordField = new PasswordField("Password for logging in (Email)");
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

        layout.addComponents(header, new Label("Please enter your credentials and log in.", ContentMode.HTML));
        return layout;
    }
}
