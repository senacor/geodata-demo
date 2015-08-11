package com.senacor.geodata.views.components;

import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

import javax.annotation.Nonnull;

/**
 * Primary button that reacts to enter.
 *
 * @author dschmitz
 */
public class BasicPrimaryButton extends Button {
    public BasicPrimaryButton(@Nonnull String caption) {
        super(caption);

        addStyleName(ValoTheme.BUTTON_PRIMARY);
        addStyleName(ValoTheme.BUTTON_SMALL);
        setClickShortcut(ShortcutAction.KeyCode.ENTER);
    }

    public BasicPrimaryButton(@Nonnull String caption, ClickListener listener) {
        this(caption);
        if (null != listener) {
            addClickListener(listener);
        }
    }
}
