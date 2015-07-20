package com.senacor.geodata.presenter;

import com.senacor.geodata.views.events.SearchResultsChangedListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by mblume on 19.07.15.
 */
@RunWith(MockitoJUnitRunner.class)
public class BaseSearchPresenterTest {

    @Mock
    private Map<SearchResultsChangedListener, Boolean> listeners;

    @InjectMocks
    private AbstractSearchPresenter<String, Object> baseSearchPresenter = new AbstractSearchPresenter<String, Object>() {
        @Override
        public void executeSearch(String searchParameter) {
            // do nothing, not part of the test
        }
    };

    @Test
    public void addsSearchResultsChangedListener() {
        @SuppressWarnings("unchecked")
        SearchResultsChangedListener<Object> listener = mock(SearchResultsChangedListener.class);

        baseSearchPresenter.addSearchResultsChangedListener(listener);

        verify(listeners).put(listener, true);
    }

}