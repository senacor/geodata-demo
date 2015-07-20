package com.senacor.geodata.views.components;

import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author dschmitz
 */
public class MapPositionBoxForm extends FormLayout {
    @PropertyId("north")
    private final TextField north;
    @PropertyId("east")
    private final TextField east;
    @PropertyId("south")
    private final TextField south;
    @PropertyId("west")
    private final TextField west;

    public MapPositionBoxForm() {
        setSpacing(true);
        addStyleName(ValoTheme.FORMLAYOUT_LIGHT);

        this.north = new TextField("North");
        this.east = new TextField("East");
        this.south = new TextField("South");
        this.west = new TextField("West");

        addComponents(this.north, this.east, this.south, this.west);
        this.north.setImmediate(true);
        this.east.setImmediate(true);
        this.south.setImmediate(true);
        this.west.setImmediate(true);
    }
}
