package com.senacor.geodata.views.events;

/**
 * @author dschmitz
 */
public interface SearchResultsChangedListener<T> {
    void onSearchResultsChanged(SearchResultsChangedEvent<T> event);
}
