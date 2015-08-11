package com.senacor.geodata.views.events;

import javax.annotation.Nonnull;

/**
 * Interface for search result changes.
 *
 * @param <T> The event payload type
 * @author dschmitz
 */
public interface SearchResultsChangedListener<T> {
    /**
     * Fired if the results of a search were changed.
     *
     * @param event the search event
     */
    void onSearchResultsChanged(@Nonnull SearchResultsChangedEvent<T> event);
}
