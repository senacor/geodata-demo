package com.senacor.geodata.views.components;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

import javax.annotation.Nonnull;

/**
 * Label used as a header.
 *
 * @author dschmitz
 */
public class HeaderLabel extends Label {

    public HeaderLabel(@Nonnull String labelText) {
        this(labelText, ContentMode.TEXT);
    }

    public HeaderLabel(@Nonnull String labelText, @Nonnull ContentMode contentMode) {
        super(labelText, contentMode);

        setSizeUndefined();
        addStyleName(ValoTheme.LABEL_H1);
        addStyleName(ValoTheme.LABEL_NO_MARGIN);
    }
}
