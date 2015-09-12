package com.senacor.geodata.views.useradministration;

import com.senacor.geodata.model.User;
import com.senacor.geodata.repository.UserRepository;
import com.senacor.geodata.views.AbstractCommonView;
import com.senacor.geodata.views.components.AbstractCommonForm;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
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
public class UserAdministrationView extends AbstractCommonView {
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
        Table table = new UsersTable(null);

        container.addComponent(new PrimaryButton("Add new user", event -> {
            Window window = new Window("Add a new user");
            window.setModal(true);
            window.center();

            window.setContent(new AbstractCommonForm("User details", FontAwesome.USER) {
                @Override
                protected void init(VerticalLayout layout) {
                    CreateNewUserForm newUserForm = new CreateNewUserForm();

                    BeanFieldGroup binder = new BeanFieldGroup<>(User.class);
                    binder.setItemDataSource(new BeanItem<>(new User("", "")));
                    binder.bindMemberFields(newUserForm);

                    PrimaryButton createUser = new PrimaryButton("Create new user", event -> {
                        try {
                            binder.commit();
                            User user = (User) binder.getItemDataSource().getBean();
                            userRepository.save(user);
                            table.setContainerDataSource(new ListContainer<>(userRepository.findAll()));
                            UI.getCurrent().removeWindow(window);
                        } catch (FieldGroup.CommitException e) {
                            e.printStackTrace();
                            Notification.show("Cannot add user", "User creation failed " + e.getMessage(), Notification.Type.WARNING_MESSAGE);
                        }

                    });

                    layout.addComponents(newUserForm, createUser);
                    layout.setComponentAlignment(createUser, MIDDLE_RIGHT);
                }
            });

            UI.getCurrent().addWindow(window);
        }));

        container.addComponent(table);
    }

    @Override
    protected Component buildIntroduction() {
        return new Label("This page demonstrates basic crud handling.");
    }

    private static class CreateNewUserForm extends FormLayout {
        @PropertyId("id")
        private final TextField id;
        @PropertyId("email")
        private final TextField email;

        public CreateNewUserForm() {
            setSpacing(true);
            addStyleName(ValoTheme.FORMLAYOUT_LIGHT);

            id = new TextField("Id");
            email = new TextField("E-Mail");

            addComponents(id, email);
        }
    }

    private static class UsersTable extends Table {
        public UsersTable(String caption) {
            super(caption);

            setSizeUndefined();
            setWidth(100, Unit.PERCENTAGE);
            setPageLength(5);

            setColumnHeader("id", "Id");
            setColumnHeader("email", "E-Mail");

            setSelectable(true);
            setNullSelectionAllowed(false);

            addItemClickListener(event -> {
            });
        }
    }
}
