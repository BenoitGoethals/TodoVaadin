package be.dragoncave.util;

import be.dragoncave.domain.Country;

import java.util.List;

/**
 * Created by benoit on 02/11/2016.
 */
public interface CountryConverter {
    List<Country> parse(String path);
}
