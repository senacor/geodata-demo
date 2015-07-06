package com.senacor.geodata.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;

import static com.senacor.geodata.views.components.ComponentUtil.buildHeaderLabel;

/**
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

        addComponent(buildHeaderLabel(getHeaderCaption()));

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

    protected abstract String getHeaderCaption();

    protected abstract void addContentsTo(VerticalLayout container);

    protected abstract Component buildIntroduction();
}
