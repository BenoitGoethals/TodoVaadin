package be.dragoncave.web;

/**
 * Created by benoit on 05/11/2016.
 */

import com.vaadin.data.util.converter.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class LocalDateToStringConverter implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convertToModel(String value, Class<? extends LocalDateTime> targetType, Locale locale) throws ConversionException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            return LocalDateTime.parse(value, formatter);

        } catch (DateTimeParseException ex) {
            return null;
        }
    }

    @Override
    public String convertToPresentation(LocalDateTime value, Class<? extends String> targetType, Locale locale) throws Converter.ConversionException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        try {
            return value.format(formatter);

        } catch (DateTimeParseException ex) {
            return null;
        }
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
