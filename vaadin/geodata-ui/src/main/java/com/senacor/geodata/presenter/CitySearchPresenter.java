package com.senacor.geodata.presenter;

import java.util.List;

import com.senacor.geodata.model.City;
import com.senacor.geodata.model.MapPositionBox;
import com.senacor.geodata.service.GeoDataService;
import com.senacor.geodata.views.events.SearchResultsChangedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mblume on 19.07.15.
 */
@Component
public class CitySearchPresenter extends BaseSearchPresenter<MapPositionBox> {
    @Autowired
    private GeoDataService geoDataService;

    @Override
    public void executeSearch(MapPositionBox mapPositionBox) {
        List<City> cities = this.geoDataService.findCitiesBy(mapPositionBox);
        this.listeners.keySet().forEach(listener -> listener.onSearchResultsChanged(new SearchResultsChangedEvent(cities)));
    }
}
