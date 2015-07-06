package com.senacor.geodata.views.components;

import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author dschmitz
 */
public class BasicPrimaryButton extends Button {
    public BasicPrimaryButton(String caption) {
        super(caption);

        addStyleName(ValoTheme.BUTTON_PRIMARY);
        addStyleName(ValoTheme.BUTTON_SMALL);
        setClickShortcut(ShortcutAction.KeyCode.ENTER);
    }
}
