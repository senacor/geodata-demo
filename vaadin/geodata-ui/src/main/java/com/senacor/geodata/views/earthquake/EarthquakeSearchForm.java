package com.senacor.geodata.views.earthquake;

import com.senacor.geodata.model.MapPositionBox;
import com.senacor.geodata.presenter.EartquakeSearchPresenter;
import com.senacor.geodata.views.components.AbstractCommonForm;
import com.senacor.geodata.views.components.BasicPrimaryButton;
import com.senacor.geodata.views.components.MapPositionBoxForm;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import static com.vaadin.ui.Alignment.MIDDLE_RIGHT;
import static com.vaadin.ui.Notification.Type.TRAY_NOTIFICATION;
import static com.vaadin.ui.Notification.Type.WARNING_MESSAGE;
import static com.vaadin.ui.Notification.show;

/**
 * @author dschmitz
 */
public class EarthquakeSearchForm extends AbstractCommonForm {

    private EartquakeSearchPresenter earthquakeSearchPresenter;

    public EarthquakeSearchForm(EartquakeSearchPresenter earthquakeSearchPresenter) {
        super("Provide map position box parameters", FontAwesome.SEARCH);
        this.earthquakeSearchPresenter = earthquakeSearchPresenter;
    }

    @Override
    protected void init(VerticalLayout layout) {
        MapPositionBox mapPositionBox = new MapPositionBox(44.1d, -9.9d, -22.4d, 55.2d);
        MapPositionBoxForm form = new MapPositionBoxForm();

        BeanFieldGroup binder = new BeanFieldGroup<>(MapPositionBox.class);
        binder.setItemDataSource(new BeanItem<>(mapPositionBox));
        binder.bindMemberFields(form);

        Button searchQuakes = new BasicPrimaryButton("Search earthquakes");
        searchQuakes.setDisableOnClick(true);
        searchQuakes.addClickListener(event -> {
            try {
                binder.commit();

                UI.getCurrent().access(() -> show("Searching...", "Looking for coordinates " + mapPositionBox, TRAY_NOTIFICATION));

                earthquakeSearchPresenter.executeSearch(mapPositionBox);
            } catch (FieldGroup.CommitException e) {
                show("Cannot look for " + mapPositionBox + "! ",  "Check form for errors (" + e.getMessage() + ")", WARNING_MESSAGE);
            }
            searchQuakes.setEnabled(true);
        });

        setSizeUndefined();
        layout.addComponent(form);
        layout.addComponent(searchQuakes);
        layout.setComponentAlignment(searchQuakes, MIDDLE_RIGHT);
    }
}
