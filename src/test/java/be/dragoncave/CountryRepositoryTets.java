package be.dragoncave;

import be.dragoncave.domain.Country;
import be.dragoncave.domain.User;
import be.dragoncave.persistance.CountryRepository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by benoit on 02/11/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class CountryRepositoryTets {

    @Autowired
    private CountryRepository countryRepository;


    @Test
    public void saveCountry() {


        Arrays.asList(new Country("Belgie"), new Country("Nederland")).parallelStream().forEach(f -> this.countryRepository.save(f));
        assertEquals(2, countryRepository.count());

    }
}
