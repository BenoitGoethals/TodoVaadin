package be.dragoncave.util;

import be.dragoncave.domain.Country;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by benoit on 02/11/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CountryConverterTest {

    @Autowired
    private CountryConverter countryConverter;

    @Test
    public void parse() throws Exception {
        assertNotNull(countryConverter);
        List<Country> countries = countryConverter.parse("src/main/resources/countries.xml");

        assertEquals(countries.size(), 250);


        assertFalse(countries.parallelStream().anyMatch(f -> f.getCountryName().isEmpty()));
    }

}