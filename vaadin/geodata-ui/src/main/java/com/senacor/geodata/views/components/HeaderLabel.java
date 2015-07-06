package com.senacor.geodata.views.components;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author dschmitz
 */
public class HeaderLabel extends Label {

    public HeaderLabel(String content) {
        this(content, ContentMode.TEXT);
    }

    public HeaderLabel(String content, ContentMode contentMode) {
        super(content, contentMode);

        setSizeUndefined();
        addStyleName(ValoTheme.LABEL_H1);
        addStyleName(ValoTheme.LABEL_NO_MARGIN);
    }
}
