package com.senacor.geodata.presenter;

import com.senacor.geodata.views.events.SearchResultsChangedListener;

import java.util.Map;
import java.util.WeakHashMap;

import static java.lang.Boolean.TRUE;

/**
 * Created by mblume on 19.07.15.
 */
public abstract class AbstractSearchPresenter<M, T> implements SearchPresenter<M, T> {

    // TODO: Thread safe?
    Map<SearchResultsChangedListener<T>, Boolean> listeners = new WeakHashMap<>();

    public final void addSearchResultsChangedListener(SearchResultsChangedListener<T> listener) {
        this.listeners.put(listener, TRUE);
    }
}
