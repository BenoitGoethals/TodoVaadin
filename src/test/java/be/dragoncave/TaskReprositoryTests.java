package be.dragoncave;

import be.dragoncave.domain.*;
import be.dragoncave.persistance.CountryRepository;
import be.dragoncave.persistance.TaskReprository;
import be.dragoncave.persistance.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by benoit on 01/11/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
//@Transactional( propagation = Propagation.REQUIRED)
public class TaskReprositoryTests {

    @Autowired
    private TaskReprository taskReprository;

    @Autowired
    private CountryRepository countryRepository;


    @Autowired
    private UserRepository userRepository;

    @Test
    @Rollback(value = true)
    public void saveTask() throws Exception {

        LocalDateTime timePoint = LocalDateTime.now();     // The current date and time

        LocalDateTime endDate = LocalDateTime.now().plusMonths(2);
        Arrays.asList(new Country("Belgie"), new Country("Nederland")).parallelStream().forEach(f -> this.countryRepository.save(f));
        Country country = countryRepository.findOne(1);
        assertNotNull(country);
        User persUser = new User("xwcwx", "sdd", "dqd", "dsqd", "9899", "dfsdf", country, endDate);
        this.userRepository.save(persUser);
        User user = userRepository.findAll().iterator().next();
        assertEquals(1, userRepository.count());
//        assertThat(userRepository.findByUserID("dqd").getCountry().getCountryName()).isEqualTo("Belgie");

        Task taskPers = new Task("ffsdf", timePoint, endDate, TaskType.PRIVATE, TaskStatus.RUNNING);
        User userRet = userRepository.findByUserID("dqd");
        assertNotNull(userRet);
        taskPers.setUser(userRet);
        this.taskReprository.save(taskPers);
        Task task = taskReprository.findAll().iterator().next();
        assertThat(task.getDescription()).isEqualTo("ffsdf");


        User userSave = userRepository.findOne(1);
        assertEquals(userSave.getTasks().size(), 1);
    }
}
