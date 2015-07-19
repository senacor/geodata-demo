package com.senacor.geodata.presenter;

import static java.lang.Boolean.TRUE;

import java.util.List;
import java.util.WeakHashMap;

import com.senacor.geodata.model.Zipcode;
import com.senacor.geodata.model.ZipcodeSearchParameter;
import com.senacor.geodata.service.GeoDataService;
import com.senacor.geodata.views.events.SearchResultsChangedEvent;
import com.senacor.geodata.views.events.SearchResultsChangedListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mblume on 19.07.15.
 */
@Component
public class ZipcodeSearchPresenter implements SearchPresenter<ZipcodeSearchParameter> {

    private WeakHashMap<SearchResultsChangedListener, Boolean> listeners = new WeakHashMap<>();

    @Autowired
    private GeoDataService geoDataService;

    @Override
    public void executeSearch(ZipcodeSearchParameter searchParameter) {
        List<Zipcode> zipcodes = geoDataService.findZipcodes(searchParameter);
        this.listeners.keySet().forEach(listener -> listener.onSearchResultsChanged(new SearchResultsChangedEvent(zipcodes)));
    }

    @Override
    public void addSearchResultsChangedListener(SearchResultsChangedListener listener) {
        this.listeners.put(listener, TRUE);
    }
}
