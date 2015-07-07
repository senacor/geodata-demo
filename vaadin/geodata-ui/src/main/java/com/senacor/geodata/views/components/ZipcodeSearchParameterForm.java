package com.senacor.geodata.views.components;

import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by mblume on 07.07.15.
 */
public class ZipcodeSearchParameterForm extends FormLayout {

    @PropertyId("zipcode")
    private final TextField zipcode;
    @PropertyId("countryCode")
    private final TextField countryCode;
    @PropertyId("diameter")
    private final TextField diameter;

    public ZipcodeSearchParameterForm() {
        setSpacing(true);
        addStyleName(ValoTheme.FORMLAYOUT_LIGHT);

        this.zipcode = new TextField("zipcode");
        this.countryCode = new TextField("countryCode");
        this.diameter = new TextField("diameter");

        addComponents(this.countryCode, this.zipcode, this.diameter);
    }

}
