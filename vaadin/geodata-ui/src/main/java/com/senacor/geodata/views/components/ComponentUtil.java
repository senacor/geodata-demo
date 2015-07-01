package com.senacor.geodata.views.components;

import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author dschmitz
 */
public class ComponentUtil {
    public static Label buildHeaderLabel(String title) {
        Label titleLabel = new Label(title);
        titleLabel.setSizeUndefined();
        titleLabel.addStyleName(ValoTheme.LABEL_H1);
        titleLabel.addStyleName(ValoTheme.LABEL_NO_MARGIN);

        return titleLabel;
    }

    public static Button buildPrimaryButton(String caption) {
        Button button = new Button(caption);
        button.addStyleName(ValoTheme.BUTTON_PRIMARY);
        button.addStyleName(ValoTheme.BUTTON_SMALL);
        button.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        return button;
    }
}
