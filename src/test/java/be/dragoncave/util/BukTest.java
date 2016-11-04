package be.dragoncave.util;

import be.dragoncave.domain.*;
import be.dragoncave.persistance.CountryRepository;
import be.dragoncave.persistance.TaskReprository;
import be.dragoncave.persistance.UserRepository;
import be.dragoncave.service.TaskService;
import be.dragoncave.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.StreamSupport;

/**
 * Created by benoit on 04/11/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BukTest {

    @Autowired
    private TaskReprository taskReprository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CountryConverter countryConverter;


    @Test
    public void loadTest() {
load();
    }


    private void load() {

        List<Country> countries = countryConverter.parse("src/main/resources/countries.xml");
        countryRepository.deleteAll();
        countryRepository.save(countries);
        System.out.println("->"+countryRepository.count());

        StreamSupport.stream(countryRepository.findAll().spliterator(),false).forEach(c-> {

            for (int i = 1; i <= 20; i++) {
                User persUser = new User("xwcwx" + i, "sdd" + i, "dqd" + i+c.getCountryName(), "dsqd" + i, "9899", "dfsdf", c, LocalDateTime.now().minusYears(50));
                userService.save(persUser);
                Task taskPers = new Task("ffsdf" + i, LocalDateTime.now().plusMonths(i), LocalDateTime.now().plusMonths(i).plusDays(20), TaskType.PRIVATE, TaskStatus.RUNNING);
                taskPers.setUser(persUser);
                taskService.save(taskPers);
            //    System.out.print(taskPers.getId());

            }
        });
        countryRepository.deleteAll();
    }


}
