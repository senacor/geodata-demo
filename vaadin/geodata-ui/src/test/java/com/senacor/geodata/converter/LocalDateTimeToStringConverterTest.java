package com.senacor.geodata.converter;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static java.util.Locale.GERMAN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * No Timezone or nano testing for now...
 *
 * @author dschmitz
 */
public class LocalDateTimeToStringConverterTest {

    private static final LocalDateTime DATE_TIME_VALUE = LocalDateTime.of(1976, Month.SEPTEMBER, 8, 19, 16, 10);

    @Test
    public void a_local_date_is_converted_to_an_iso_date_string() {
        String result = new LocalDateTimeToStringConverter().convertToPresentation(DATE_TIME_VALUE, null, GERMAN);

        //2011-12-03T10:15:30+01:00
        assertEquals("1976-09-08T19:16:10", result);
    }

    @Test
    public void null_local_dates_are_converted_to_empty_strings() {
        String result = new LocalDateTimeToStringConverter().convertToPresentation(null, null, GERMAN);

        assertEquals("", result);
    }

    @Test
    public void null_strings_are_converted_to_null_localdatetimes() {
        LocalDateTime result = new LocalDateTimeToStringConverter().convertToModel(null, LocalDateTime.class, GERMAN);

        assertNull(result);
    }

    @Test
    public void empty_strings_are_converted_to_null_localdatetimes() {
        LocalDateTime result = new LocalDateTimeToStringConverter().convertToModel("", LocalDateTime.class, GERMAN);

        assertNull(result);
    }

    @Test
    public void valid_iso_strings_are_converted_to_localdatetimes() {
        LocalDateTime result = new LocalDateTimeToStringConverter().convertToModel("2015-10-01T12:12:10", LocalDateTime.class, GERMAN);

        assertEquals(LocalDateTime.of(2015, Month.OCTOBER, 1, 12, 12, 10), result);
    }
}