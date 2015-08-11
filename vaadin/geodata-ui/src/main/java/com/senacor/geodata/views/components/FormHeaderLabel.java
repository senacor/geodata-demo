package com.senacor.geodata.views.components;

import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

import javax.annotation.Nonnull;

/**
 * Header label used for forms.
 *
 * @author dschmitz
 */
public class FormHeaderLabel extends Label {

    public FormHeaderLabel(@Nonnull String labelText) {
        super(labelText);
        setStyleName(ValoTheme.LABEL_H3);
    }
}
