package com.senacor.geodata.views.events;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

/**
 * Immutable event for signalling changes to the city search results.
 *
 * @param <T> The event payload type
 * @author dschmitz
 */
public class SearchResultsChangedEvent<T> {
    private final List<T> searchResult;

    public SearchResultsChangedEvent(@Nonnull List<T> searchResult) {
        this.searchResult = Collections.unmodifiableList(searchResult);
    }

    public List<T> getSearchResult() {
        return searchResult;
    }
}
