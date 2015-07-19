package com.senacor.geodata.presenter;

import static java.lang.Boolean.TRUE;

import java.util.Map;
import java.util.WeakHashMap;

import com.senacor.geodata.views.events.SearchResultsChangedListener;

/**
 * Created by mblume on 19.07.15.
 */
public abstract class BaseSearchPresenter<M> implements SearchPresenter<M> {

    Map<SearchResultsChangedListener, Boolean> listeners = new WeakHashMap<>();

    public void addSearchResultsChangedListener(SearchResultsChangedListener listener) {
        this.listeners.put(listener, TRUE);
    }
}
