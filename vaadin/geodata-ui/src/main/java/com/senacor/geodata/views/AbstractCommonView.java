package com.senacor.geodata.views;

import com.senacor.geodata.views.components.HeaderLabel;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;

/**
 * Base view providing a vertical layout for the typical UI parts of the application.
 *
 * @author dschmitz
 */
public abstract class AbstractCommonView extends VerticalLayout implements View {

    public AbstractCommonView() {
    }

    @PostConstruct
    public void initCommonLayout() {
        setSizeUndefined();
        setWidth(100, Unit.PERCENTAGE);
        setSpacing(true);

        addComponent(new HeaderLabel(getHeaderCaption()));

        addComponent(buildIntroduction());

        final VerticalLayout container = new VerticalLayout();
        container.setHeightUndefined();
        container.setWidth(100, Unit.PERCENTAGE);
        container.setSpacing(true);
        addComponent(container);

        addContentsTo(container);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    /**
     * Hook method for adding captions.
     *
     * @return the caption
     */
    protected abstract String getHeaderCaption();

    /**
     * Hook method for adding contents to the layout.
     *
     * @param container the layout items may be added to
     */
    protected abstract void addContentsTo(VerticalLayout container);

    /**
     * Hook method that provids an introduction text.
     *
     * @return the introduction text
     */
    protected abstract Component buildIntroduction();
}
