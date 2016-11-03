package be.dragoncave;

import be.dragoncave.domain.*;
import be.dragoncave.persistance.CountryRepository;
import be.dragoncave.persistance.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Created by benoit on 02/11/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class UserRepositoryTets {

    @Autowired
    private CountryRepository countryRepository;


    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveTask() throws Exception {

        LocalDateTime birthDate = LocalDateTime.now().plusMonths(2);
        Arrays.asList(new Country("Belgie"), new Country("Nederland")).parallelStream().forEach(f -> this.countryRepository.save(f));
        User persUser = new User("xwcwx", "sdd", "dqd", "dsqd", "9899", "dfsdf", countryRepository.findOne(1), birthDate);
        this.userRepository.save(persUser);
        User user = userRepository.findAll().iterator().next();
        assertEquals(1, userRepository.count());
        assertThat(userRepository.findByUserID("dqd").getCountry().getCountryName()).isEqualTo("Belgie");
    }
}
