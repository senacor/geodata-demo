package com.senacor.geodata.views.components;

import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author dschmitz
 */
public class FormHeaderLabel extends Label {
    public FormHeaderLabel(String text) {
        super(text);
        setStyleName(ValoTheme.LABEL_H3);
    }
}
