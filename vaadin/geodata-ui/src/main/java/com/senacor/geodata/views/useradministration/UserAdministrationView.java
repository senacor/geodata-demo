package com.senacor.geodata.views.useradministration;

import com.senacor.geodata.model.User;
import com.senacor.geodata.repository.UserRepository;
import com.senacor.geodata.views.AbstractCommonView;
import com.senacor.geodata.views.components.AbstractCommonForm;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.viritin.ListContainer;
import org.vaadin.viritin.button.PrimaryButton;

import javax.annotation.Nonnull;

import static com.vaadin.ui.Alignment.MIDDLE_RIGHT;

/**
 * @author dschmitz
 */
@SpringView(name = UserAdministrationView.VIEW_NAME)
public class UserAdministrationView  extends AbstractCommonView {
    public static final String VIEW_NAME = "UserAdministrationView";
    private final UserRepository userRepository;

    @Autowired
    public UserAdministrationView(@Nonnull UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    protected String getHeaderCaption() {
        return "User Administration";
    }

    @Override
    protected void addContentsTo(VerticalLayout container) {
        Table table = new Table();

        container.addComponent(new PrimaryButton("Add new user", event -> {
            Window window = new Window("Add a new user");
            window.setModal(true);
            window.setResizable(true);
            window.center();

            window.setContent(new AbstractCommonForm("User details", FontAwesome.USER) {
                @Override
                protected void init(VerticalLayout layout) {
                    FormLayout formLayout = new FormLayout();
                    formLayout.setSpacing(true);
                    formLayout.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);

                    TextField id = new TextField("Id");
                    TextField email = new TextField("E-Mail");

                    formLayout.addComponents(id, email);

                    PrimaryButton createUser = new PrimaryButton("Create new user", event -> {
                        userRepository.save(new User(id.getValue(), email.getValue()));
                        table.setContainerDataSource(new ListContainer<>(userRepository.findAll()));
                        UI.getCurrent().removeWindow(window);
                    });

                    layout.addComponent(formLayout);
                    layout.addComponent(createUser);
                    layout.setComponentAlignment(createUser, MIDDLE_RIGHT);
                }
            });

            UI.getCurrent().addWindow(window);
        }));

        table.setSizeUndefined();
        table.setWidth(100, Unit.PERCENTAGE);
        table.setPageLength(5);

        table.setColumnHeader("id", "Id");
        table.setColumnHeader("email", "E-Mail");
        table.setContainerDataSource(new ListContainer<>(userRepository.findAll()));

        table.setSelectable(true);
        table.setNullSelectionAllowed(false);

        table.addItemClickListener(event -> {
            System.out.println("ITEM SELECTED " + event.getItem());
        });

        container.addComponent(table);
    }

    @Override
    protected Component buildIntroduction() {
        return new Label("This page demonstrates basic crud handling.");
    }
}
