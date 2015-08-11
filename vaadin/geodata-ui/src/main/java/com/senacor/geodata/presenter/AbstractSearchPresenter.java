package com.senacor.geodata.presenter;

import com.senacor.geodata.views.events.SearchResultsChangedListener;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.Consumer;

import static java.lang.Boolean.TRUE;

/**
 * Created by mblume on 19.07.15.
 */
abstract class AbstractSearchPresenter<M, T> implements SearchPresenter<M, T> {

    private Map<SearchResultsChangedListener<T>, Boolean> listeners = Collections.<SearchResultsChangedListener<T>, Boolean>synchronizedMap(new WeakHashMap<>());

    public final void addSearchResultsChangedListener(@Nonnull SearchResultsChangedListener<T> listener) {
        this.listeners.put(listener, TRUE);
    }

    protected void forEach(Consumer<SearchResultsChangedListener<T>> consumer) {
        listeners.keySet().forEach(consumer);
    }
}
