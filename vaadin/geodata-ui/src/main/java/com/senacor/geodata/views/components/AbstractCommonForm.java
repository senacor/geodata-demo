package com.senacor.geodata.views.components;

import com.vaadin.server.Resource;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;


/**
 * Abstract base panel that is used for forms.
 *
 * @author dschmitz
 */
public abstract class AbstractCommonForm extends Panel {
    /**
     *
     * @param caption the caption of the panel
     * @param icon the icon next to the caption
     */
    public AbstractCommonForm(String caption, Resource icon) {
        super(caption);
        setIcon(icon);
        setCaptionAsHtml(true);
        addStyleName(ValoTheme.PANEL_WELL);
        setSizeUndefined();
        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);

        init(layout);

        super.setContent(layout);
    }

    /**
     * Hook method for adding components to the vertical layout of the panel.
     *
     * You need not call setContent inside.
     *
     * @param layout the layout components may be added to
     */
    protected abstract void init(VerticalLayout layout);
}
