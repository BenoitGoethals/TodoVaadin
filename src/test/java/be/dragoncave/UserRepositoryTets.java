package be.dragoncave;

import be.dragoncave.domain.*;
import be.dragoncave.persistance.CountryRepository;
import be.dragoncave.persistance.UserRepository;
import be.dragoncave.util.CountryConverter;
import org.apache.commons.collections.IteratorUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * Created by benoit on 02/11/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class UserRepositoryTets {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CountryConverter countryConverter;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Rollback(value = true)
    public void saveTask() throws Exception {

        LocalDateTime birthDate = LocalDateTime.now().plusMonths(2);
        List<Country> countries = countryConverter.parse("src/main/resources/countries.xml");
        assertEquals(countries.size(), 250);
        assertFalse(countries.parallelStream().anyMatch(f -> f.getCountryName().isEmpty()));
        countryRepository.save(countries);
        assertEquals(countryRepository.count(), 250);
        List<Country> countries2 = IteratorUtils.toList(countryRepository.findAll().iterator());
        assertFalse(countries2.parallelStream().anyMatch(f -> f.getCountryName().isEmpty()));
        User persUser = new User("xwcwx", "sdd", "dqd", "dsqd", "9899", "dfsdf", countries2.get(1), birthDate);
        this.userRepository.save(persUser);
        User user = userRepository.findAll().iterator().next();
        assertEquals(1, userRepository.count());
        assertNotNull(userRepository.findByUserID("dqd").getCountry());
        countryRepository.deleteAll();
    }
}
