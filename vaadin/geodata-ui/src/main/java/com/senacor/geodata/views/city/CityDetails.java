package com.senacor.geodata.views.city;

import com.senacor.geodata.model.City;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import static com.senacor.geodata.views.components.ComponentUtil.buildFormHeader;
import static com.senacor.geodata.views.components.ComponentUtil.buildPrimaryButton;

/**
 * @author dschmitz
 */
public class CityDetails extends VerticalLayout implements Property.ValueChangeListener {
    @PropertyId("name")
    private final TextField name;
    @PropertyId("country")
    private final TextField country;
    @PropertyId("wikipedia")
    private final TextField wikipedia;

    private City city;

    public CityDetails(City value) {
        setSpacing(true);
        this.city = value;
        addComponent(buildFormHeader("City details"));

        FormLayout form = new FormLayout();
        form.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
        this.name = new TextField("Name");
        this.name.setReadOnly(true);
        this.country = new TextField("Country");
        this.name.setReadOnly(true);
        this.wikipedia = new TextField("Wikipedia");
        this.wikipedia.setReadOnly(true);

        form.addComponents(this.name, this.country, this.wikipedia);
        addComponent(form);

        HorizontalLayout buttonBar = new HorizontalLayout();
        buttonBar.setHeightUndefined();
        buttonBar.setWidth(100, Unit.PERCENTAGE);
        addComponent(buttonBar);

        Button button = buildPrimaryButton("Show Wikipedia");
        button.addClickListener(event -> {
            Window window = new Window();
            window.center();
            window.setSizeUndefined();
            BrowserFrame browser = new BrowserFrame("Browser",
                    new ExternalResource("http://" + this.wikipedia.getValue().replace("en.wikipedia", "en.m.wikipedia")));
            browser.setWidth("600px");
            browser.setHeight("400px");

            window.setContent(browser);

            UI.getCurrent().addWindow(window);
        });

        buttonBar.addComponent(button);

        Button mapsbutton = buildPrimaryButton("Show in Maps");
        mapsbutton.addClickListener(event -> {
            Window window = new Window();
            window.center();
            window.setSizeUndefined();

            String url = String.format("https://www.bing.com/maps/embed/viewer.aspx?v=3&cp=%s~%s&lvl=12&w=600&h=400&sty=r&typ=s&pp=&ps=55&dir=0&mkt=de-de&src=SHELL&form=BMEMJS", this.city.getMapPosition().getLatitute(), this.city.getMapPosition().getLongitude());

            BrowserFrame browser = new BrowserFrame("Browser",
                    new ExternalResource(url));
            browser.setWidth("600px");
            browser.setHeight("400px");

            window.setContent(browser);

            UI.getCurrent().addWindow(window);
        });
        buttonBar.addComponent(mapsbutton);
        buttonBar.setDefaultComponentAlignment(ALIGNMENT_DEFAULT.MIDDLE_RIGHT);
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        City value = (City) event.getProperty().getValue();
        setVisible(null != value);

        if (null == value) {
            value = new City();
        }

        FieldGroup binder = new FieldGroup();
        binder.setItemDataSource(new BeanItem<>(value));
        binder.bindMemberFields(this);

        this.city = value;
    }
}
