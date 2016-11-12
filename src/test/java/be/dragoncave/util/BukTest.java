package be.dragoncave.util;

import be.dragoncave.domain.*;
import be.dragoncave.persistance.CountryRepository;
import be.dragoncave.persistance.TaskReprository;
import be.dragoncave.persistance.UserRepository;
import be.dragoncave.service.SecurityService;
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

/**
 * Created by benoit on 04/11/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BukTest {


    @Autowired
    private SecurityService securityService;


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

        taskService.deleteAll();
        userService.deleteAll();
        securityService.deleteAll();
        countryRepository.deleteAll();
        countryRepository.save(countries);
        List<Country> countriesArray = (List<Country>) countryRepository.findAll();

        Role role=new Role("admin");



        Role role1=new Role("user");

        Role role2= securityService.addRole(role);
        securityService.addRole(role1);


        UserDetail user=new UserDetail("benoit","ranger14",true);
        user.setRole(role2);
        securityService.addUserDetail(user);
        User persUsers = new User("benoit" , "sddssddsq" , "dqd" , "dsqssd", "9899", "dfsdf", countriesArray.get(2), LocalDateTime.now().minusYears(50));

        user.setRole(role2);

        System.out.println(securityService.countUserDetaisl());
        persUsers.setUserDetail(securityService.addUserDetail(user));
        userService.save(persUsers);


        System.out.println(countryRepository.count());

        for (int i = 1; i <= 10; i++) {
            User persUser = new User("xwcwx" + i, "sdd" + i, "dqd" + i, "dsqd" + i, "9899", "dfsdf", countriesArray.get(i), LocalDateTime.now().minusYears(50));
            userService.save(persUser);
            Task taskPers = new Task("ffsdf" + i, LocalDateTime.now().plusMonths(i), LocalDateTime.now().plusMonths(i).plusDays(20), TaskType.PRIVATE, TaskStatus.RUNNING);
            taskPers.setUser(persUser);
            taskService.save(taskPers);
            System.out.print(taskPers.getId());
        }
        countryRepository.deleteAll();
    }


}
