package com.senacor.geodata.views.components;

import com.senacor.geodata.GeoDataUI;
import com.senacor.geodata.views.city.CitySearchView;
import com.senacor.geodata.views.earthquake.EarthquakeStatisticsView;
import com.senacor.geodata.views.postalcode.PostalCodeView;
import com.senacor.geodata.views.useradministration.UserAdministrationView;
import com.senacor.geodata.views.weather.WeatherInformationView;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import javax.annotation.Nonnull;

/**
 * Component that represents a collapsing menu positioned at the left side.
 *
 * @author dschmitz
 */
public class MenuBar extends CssLayout {
    public MenuBar() {
        setPrimaryStyleName(ValoTheme.MENU_ROOT);
        setSizeUndefined();
        setHeight(100, Unit.PERCENTAGE);

        addComponent(buildMenu());
    }

    protected void addMenuItems(@Nonnull CssLayout menuItems) {
        menuItems.addComponent(new MainNavigationMenuItemButton("City search", FontAwesome.SEARCH, CitySearchView.VIEW_NAME));

        menuItems.addComponent(new MainNavigationMenuItemButton("Weather information", FontAwesome.SUN_O, WeatherInformationView.VIEW_NAME));

        menuItems.addComponent(new MainNavigationMenuItemButton("Postal code", FontAwesome.BUILDING, PostalCodeView.VIEW_NAME));

        menuItems.addComponent(new MainNavigationMenuItemButton("Earthquake statistics", FontAwesome.BAR_CHART_O, EarthquakeStatisticsView.VIEW_NAME));

        menuItems.addComponent(new MainNavigationMenuItemButton("User administration", FontAwesome.USER, UserAdministrationView.VIEW_NAME));

        menuItems.addComponent(buildLogout());
    }

    private Component buildMenu() {
        CssLayout menuContent = new CssLayout();

        menuContent.addStyleName(ValoTheme.MENU_PART);
        menuContent.addComponent(addMenuTitle());
        final CssLayout menuItems = new CssLayout();
        menuItems.setPrimaryStyleName("valo-menuitems");
        addMenuItems(menuItems);

        final Button showMenu = new Button("Menu", event -> {
            if (menuContent.getStyleName().contains("valo-menu-visible")) {
                menuContent.removeStyleName("valo-menu-visible");
            } else {
                menuContent.addStyleName("valo-menu-visible");
            }
        });
        showMenu.addStyleName(ValoTheme.BUTTON_PRIMARY);
        showMenu.addStyleName(ValoTheme.BUTTON_SMALL);
        showMenu.addStyleName("valo-menu-toggle");
        showMenu.setIcon(FontAwesome.LIST);
        menuContent.addComponent(showMenu);
        menuContent.addComponent(menuItems);

        return menuContent;
    }

    private Component buildLogout() {
        Button logout = new MainNavigationMenuItemButton("Logout", FontAwesome.SIGN_OUT, (event) -> {
            Notification.show("You are being logged out...", Notification.Type.TRAY_NOTIFICATION);
            // evil and stupid...refactor after login is implemented
            ((GeoDataUI) UI.getCurrent()).userLoggedOut();
        });

        logout.addStyleName("geodata-menu-logout");
        return logout;
    }

    private static class MainNavigationMenuItemButton extends Button {
        public MainNavigationMenuItemButton(@Nonnull String title, @Nonnull Resource icon, final @Nonnull String targetView) {
            this(title, icon, event -> UI.getCurrent().getNavigator().navigateTo(targetView));
        }

        public MainNavigationMenuItemButton(@Nonnull String title, @Nonnull Resource icon, @Nonnull ClickListener clickListener) {
            super(title);
            setIcon(icon);
            setPrimaryStyleName(ValoTheme.MENU_ITEM);
            addStyleName("geodata-menu-item");
            addClickListener(clickListener);
        }
    }

    private Component addMenuTitle() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setHeightUndefined();
        layout.setWidth(100, Unit.PERCENTAGE);

        Label label = new Label("<strong>geoData</strong>", ContentMode.HTML);
        label.addStyleName(ValoTheme.MENU_TITLE);
        layout.addComponent(label);
        return layout;
    }
}
