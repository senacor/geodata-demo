package com.senacor.geodata.presenter;

import com.senacor.geodata.model.Zipcode;
import com.senacor.geodata.model.ZipcodeSearchParameter;
import com.senacor.geodata.service.GeoDataService;
import com.senacor.geodata.views.events.SearchResultsChangedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mblume on 19.07.15.
 */
@Component
public class ZipcodeSearchPresenter extends AbstractSearchPresenter<ZipcodeSearchParameter, Zipcode> {

    @Autowired
    private GeoDataService geoDataService;

    @Override
    public void executeSearch(ZipcodeSearchParameter searchParameter) {
        List<Zipcode> zipcodes = geoDataService.findZipcodes(searchParameter);
        this.listeners.keySet().forEach(listener -> listener.onSearchResultsChanged(new SearchResultsChangedEvent<>(zipcodes)));
    }

}
