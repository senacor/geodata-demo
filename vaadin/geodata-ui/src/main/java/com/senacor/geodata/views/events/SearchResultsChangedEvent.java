package com.senacor.geodata.views.events;

import java.util.Collections;
import java.util.List;

/**
 * Immutable event for signalling changes to the city search results.
 *
 * @author dschmitz
 */
public class SearchResultsChangedEvent<T> {
    private final List<T> searchResult;

    public SearchResultsChangedEvent(List<T> searchResult) {
        this.searchResult = Collections.unmodifiableList(searchResult);
    }

    public List<T> getSearchResult() {
        return searchResult;
    }
}
