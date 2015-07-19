package com.senacor.geodata.presenter;

import com.senacor.geodata.views.events.SearchResultsChangedListener;

/**
 * Created by mblume on 19.07.15.
 */
public interface SearchPresenter<M> {

    void executeSearch(M searchParameter);

    void addSearchResultsChangedListener(SearchResultsChangedListener listener);
}
