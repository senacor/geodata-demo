package com.senacor.geodata.views.components;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

/**
 * @author dschmitz
 */
public class ComponentUtil {
    public static Label buildHeaderLabel(String title) {
        return new HeaderLabel(title);
    }

    public static Button buildPrimaryButton(String caption) {
        return new BasicPrimaryButton(caption);
    }

    public static Component buildFormHeader(String caption) {
        return new FormHeaderLabel(caption);
    }

}
