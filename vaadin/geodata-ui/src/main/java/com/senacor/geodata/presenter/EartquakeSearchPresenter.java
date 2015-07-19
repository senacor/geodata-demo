package com.senacor.geodata.presenter;

import static java.lang.Boolean.TRUE;

import java.util.List;
import java.util.WeakHashMap;

import com.senacor.geodata.model.Earthquake;
import com.senacor.geodata.model.MapPositionBox;
import com.senacor.geodata.service.GeoDataService;
import com.senacor.geodata.views.events.SearchResultsChangedEvent;
import com.senacor.geodata.views.events.SearchResultsChangedListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mblume on 19.07.15.
 */
@Component
public class EartquakeSearchPresenter implements SearchPresenter<MapPositionBox> {
    @Autowired
    private GeoDataService geoDataService;
    private WeakHashMap<SearchResultsChangedListener, Boolean> listeners = new WeakHashMap<>();

    @Override
    public void executeSearch(MapPositionBox searchParameter) {
        List<Earthquake> earthquakes = this.geoDataService.findRecentEarthquakesWithin(searchParameter);
        this.listeners.keySet().forEach(listener -> listener.onSearchResultsChanged(new SearchResultsChangedEvent(earthquakes)));
    }

    @Override
    public void addSearchResultsChangedListener(SearchResultsChangedListener listener) {
        this.listeners.put(listener, TRUE);
    }

}
