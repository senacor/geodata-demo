package com.senacor.geodata.presenter;

import com.senacor.geodata.model.City;
import com.senacor.geodata.model.MapPositionBox;
import com.senacor.geodata.service.GeoDataService;
import com.senacor.geodata.views.events.SearchResultsChangedEvent;
import com.senacor.geodata.views.events.SearchResultsChangedListener;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;

/**
 * Created by mblume on 19.07.15.
 */
@RunWith(MockitoJUnitRunner.class)
public class CitySearchPresenterTest extends TestCase {

    @Mock
    private GeoDataService geoDataService;
    @Mock
    private MapPositionBox mapPositionBox;
    @Mock
    private SearchResultsChangedListener<City> listener;
    @Mock
    private City city1;
    @Mock
    private City city2;
    @Captor
    private ArgumentCaptor<SearchResultsChangedEvent<City>> citesSearchResultChangedEvent;

    private java.util.List<City> cities;

    @InjectMocks
    private CitySearchPresenter citySearchPresenter;

    @Before
    public void setup() {
        cities = Arrays.asList(city1, city2);
    }

    @Test
    public void executeSearch_callsCityService() {
        citySearchPresenter.executeSearch(mapPositionBox);

        verify(geoDataService).findCitiesBy(mapPositionBox);
    }

    @Test
    public void executeSearch_callsListenersWithSearchResult() {
        Mockito.when(geoDataService.findCitiesBy(mapPositionBox)).thenReturn(cities);

        citySearchPresenter.addSearchResultsChangedListener(listener);
        citySearchPresenter.executeSearch(mapPositionBox);

        verify(listener).onSearchResultsChanged(citesSearchResultChangedEvent.capture());
        assertThat(citesSearchResultChangedEvent.getValue().getSearchResult()).containsExactly(city1, city2);
    }
}