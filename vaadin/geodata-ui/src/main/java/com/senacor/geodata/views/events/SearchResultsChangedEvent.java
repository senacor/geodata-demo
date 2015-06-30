package com.senacor.geodata.views.events;

import com.senacor.geodata.model.City;

import java.util.Collections;
import java.util.List;

/**
 * Immutable event for signalling changes to the city search results.
 *
 * @author dschmitz
 */
public class SearchResultsChangedEvent {
    private final List<City> searchResult;

    public SearchResultsChangedEvent(List<City> searchResult) {
        this.searchResult = Collections.unmodifiableList(searchResult);
    }

    public List<City> getSearchResult() {
        return searchResult;
    }
}
