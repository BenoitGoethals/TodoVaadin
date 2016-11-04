package be.dragoncave.util;

import be.dragoncave.domain.*;
import be.dragoncave.persistance.CountryRepository;
import be.dragoncave.service.TaskService;
import be.dragoncave.service.UserService;
import be.dragoncave.util.CountryConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by benoit on 04/11/2016.
 */
//@SpringBootConfiguration()
public class AppConfiguration {


    @Autowired
    Environment env;
/*
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private CountryConverter countryConverter;

    @Bean
    @Transactional
    public CommandLineRunner commandLineRunner() {


        return (args) -> {
            //  taskService.save();
            List<Country> countries = countryConverter.parse("src/main/resources/countries.xml");
            countryRepository.save(countries);



            for (int i = 1; i == 200; i++) {
                User persUser = new User("xwcwx" + i, "sdd" + i, "dqd" + i, "dsqd" + i, "9899", "dfsdf", countryRepository.findOne(i), LocalDateTime.now().minusYears(50));
                userService.save(persUser);
                Task taskPers = new Task("ffsdf" + i, LocalDateTime.now().plusMonths(i), LocalDateTime.now().plusMonths(i).plusDays(20), TaskType.PRIVATE, TaskStatus.RUNNING);
                taskPers.setUser(persUser);
                System.out.println( taskService.save(taskPers));

            }
        };
    }
    */
}
