package com.senacor.geodata.views.city;

import static com.vaadin.ui.Alignment.MIDDLE_RIGHT;
import static com.vaadin.ui.Notification.Type.TRAY_NOTIFICATION;
import static com.vaadin.ui.Notification.Type.WARNING_MESSAGE;
import static com.vaadin.ui.Notification.show;

import com.senacor.geodata.model.MapPositionBox;
import com.senacor.geodata.presenter.CitySearchPresenter;
import com.senacor.geodata.views.components.AbstractCommonForm;
import com.senacor.geodata.views.components.BasicPrimaryButton;
import com.senacor.geodata.views.components.MapPositionBoxForm;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * Search form for cities.
 *
 * @author dschmitz
 */
public class CitySearchForm extends AbstractCommonForm {
    private CitySearchPresenter citySearchPresenter;

    public CitySearchForm(CitySearchPresenter citySearchPresenter) {
        this.citySearchPresenter = citySearchPresenter;
        init(new VerticalLayout());
    }

    protected void init(VerticalLayout layout) {
        addComponent(layout);
        MapPositionBox mapPositionBox = new MapPositionBox(44.1d, -9.9d, -22.4d, 55.2d);
        MapPositionBoxForm form = new MapPositionBoxForm();

        FieldGroup binder = new FieldGroup();
        binder.setItemDataSource(new BeanItem<>(mapPositionBox));
        binder.bindMemberFields(form);

        Button searchCity = new BasicPrimaryButton("Search cities");
        searchCity.setDisableOnClick(true);
        searchCity.addClickListener(event -> {
            try {
                binder.commit();

                UI.getCurrent().access(() -> show("Searching...", "Looking for coordinates " + mapPositionBox, TRAY_NOTIFICATION));

                citySearchPresenter.executeSearch(mapPositionBox);
            } catch (FieldGroup.CommitException e) {
                show("Searching...", "Cannot look for " + mapPositionBox + "! " + e.getMessage(), WARNING_MESSAGE);
            }
            searchCity.setEnabled(true);
        });

        //        layout.setSizeUndefined();
        layout.addComponent(form);
        layout.addComponent(searchCity);
        layout.setComponentAlignment(searchCity, MIDDLE_RIGHT);
    }

}
