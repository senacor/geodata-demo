package com.senacor.geodata.converter;

import com.vaadin.data.util.converter.Converter;
import org.apache.commons.lang3.StringUtils;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author dschmitz
 */
public class LocalDateTimeToStringConverter implements Converter<String, LocalDateTime> {
    @Override
    public LocalDateTime convertToModel(String value, Class<? extends LocalDateTime> targetType, Locale locale) throws ConversionException {
        if (StringUtils.isBlank(value)) {
            return null;
        }

        try {
            return LocalDateTime.from(DateTimeFormatter.ISO_DATE_TIME.parse(value));
        }
        catch (DateTimeException e) {
            throw new ConversionException("Cannot convert " + value + " to a datetime", e);
        }
    }

    @Override
    public String convertToPresentation(LocalDateTime value, Class<? extends String> targetType, Locale locale) throws ConversionException {
        if (null == value) {
            return "";
        }

        return value.format(DateTimeFormatter.ISO_DATE_TIME);
    }

    @Override
    public Class<LocalDateTime> getModelType() {
        return LocalDateTime.class;
    }

    @Override
    public Class<String> getPresentationType() {
        return String.class;
    }
}
