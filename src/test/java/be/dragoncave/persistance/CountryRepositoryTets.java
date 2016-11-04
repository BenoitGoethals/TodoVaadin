package be.dragoncave.persistance;

import be.dragoncave.domain.Country;
import be.dragoncave.persistance.CountryRepository;
import be.dragoncave.util.CountryConverter;
import org.apache.commons.collections.IteratorUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by benoit on 02/11/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class CountryRepositoryTets {

    private static final Logger logger = LoggerFactory.getLogger(CountryRepositoryTets.class);

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CountryConverter countryConverter;

    @Test
    @Rollback(value = true)
    public void saveCountry() {
        countryRepository.deleteAll();
        List<Country> countries = countryConverter.parse("src/main/resources/countries.xml");
        assertEquals(countries.size(), 250);
        assertFalse(countries.parallelStream().anyMatch(f -> f.getCountryName().isEmpty()));
        countryRepository.save(countries);
        assertEquals(countryRepository.count(), 250);
        List<Country> countries2 = IteratorUtils.toList(countryRepository.findAll().iterator());
        assertFalse(countries2.parallelStream().anyMatch(f -> f.getCountryName().isEmpty()));
        countryRepository.deleteAll();
        logger.info("test");

    }
}
