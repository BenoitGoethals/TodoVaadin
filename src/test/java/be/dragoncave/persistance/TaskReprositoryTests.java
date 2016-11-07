package be.dragoncave.persistance;

import be.dragoncave.SpecificTestConfig;
import be.dragoncave.ToDoApplication;
import be.dragoncave.domain.*;
import be.dragoncave.util.CountryConverter;
import org.apache.commons.collections.IteratorUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * Created by benoit on 01/11/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes =SpecificTestConfig.class)
@SpringBootTest
//@DataJpaTest(showSql = true)
@ActiveProfiles({"test-profile"})
//@Transactional( propagation = Propagation.REQUIRED)
public class TaskReprositoryTests {

    @Autowired
    private TaskReprository taskReprository;

    @Autowired
    private CountryRepository countryRepository;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CountryConverter countryConverter;


    @Test
    @Rollback(value = true)
    public void saveTask() throws Exception {
     countryRepository.deleteAll();
        LocalDateTime timePoint = LocalDateTime.now();     // The current date and time

        LocalDateTime endDate = LocalDateTime.now().plusMonths(2);
        List<Country> countries = countryConverter.parse("src/main/resources/countries.xml");
        assertEquals(250,countries.size());
        assertFalse(countries.parallelStream().anyMatch(f -> f.getCountryName().isEmpty()));
        countryRepository.save(countries);
        assertEquals(250,countryRepository.count());
        List<Country> countries2 = IteratorUtils.toList(countryRepository.findAll().iterator());
        assertFalse(countries2.parallelStream().anyMatch(f -> f.getCountryName().isEmpty()));
        User persUser = new User("xwcwx", "sdd", "dqd", "dsqd", "9899", "dfsdf", countries2.get(1), endDate);
        this.userRepository.save(persUser);
        User user = userRepository.findAll().iterator().next();
        assertEquals(1, userRepository.count());
//        assertThat(userRepository.findByUserID("dqd").getCountry().getCountryName()).isEqualTo("Belgie");

        Task taskPers = new Task("ffsdf", timePoint, endDate, TaskType.PRIVATE, TaskStatus.RUNNING);
        User userRet = userRepository.findByUserID("dqd");
        assertNotNull(userRet);
        taskPers.setUser(userRet);
        Task task = this.taskReprository.save(taskPers);
        //Task task = taskReprository.findAll().iterator().next();
        assertThat(task.getDescription()).isEqualTo("ffsdf");
        assertEquals(task.getId(), 1);

        User userSave = userRepository.findOne(1);
        assertEquals(userSave.getTasks().size(), 1);
        taskReprository.deleteAll();
        userRepository.deleteAll();
        countryRepository.deleteAll();
    }
}
