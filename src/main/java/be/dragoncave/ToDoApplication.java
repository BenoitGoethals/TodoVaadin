package be.dragoncave;

import be.dragoncave.domain.*;
import be.dragoncave.persistance.CountryRepository;
import be.dragoncave.persistance.UserRepository;
import be.dragoncave.service.TaskService;
import be.dragoncave.service.UserService;
import be.dragoncave.util.CountryConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
//@ComponentScan()
public class ToDoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ToDoApplication.class, args);

    }

    @Autowired
    private TaskService taskService;
    @Autowired

    private CountryConverter countryConverter;
    @Autowired

    private UserService userService;
    @Autowired

    private CountryRepository countryRepository;


    @Bean
    public CommandLineRunner loadData() {
        List<Country> countries = countryConverter.parse("src/main/resources/countries.xml");

        countryRepository.save(countries);
        System.out.println(countryRepository.count());
        for (int i = 1; i <= 200; i++) {
            User persUser = new User("xwcwx" + i, "sdd" + i, "dqd" + i, "dsqd" + i, "9899", "dfsdf", countryRepository.findOne(i), LocalDateTime.now().minusYears(50));
            userService.save(persUser);
            Task taskPers = new Task("ffsdf" + i, LocalDateTime.now().plusMonths(i), LocalDateTime.now().plusMonths(i).plusDays(20), TaskType.PRIVATE, TaskStatus.RUNNING);
            taskPers.setUser(persUser);
            taskService.save(taskPers);
            System.out.print(taskPers.getId());

        }
        System.out.println("hallo");
        return null;
    }


    private void load() {

        List<Country> countries = countryConverter.parse("src/main/resources/countries.xml");

        countryRepository.save(countries);
        System.out.println(countryRepository.count());
        for (int i = 1; i <= 200; i++) {
            User persUser = new User("xwcwx" + i, "sdd" + i, "dqd" + i, "dsqd" + i, "9899", "dfsdf", countryRepository.findOne(i), LocalDateTime.now().minusYears(50));
            userService.save(persUser);
            Task taskPers = new Task("ffsdf" + i, LocalDateTime.now().plusMonths(i), LocalDateTime.now().plusMonths(i).plusDays(20), TaskType.PRIVATE, TaskStatus.RUNNING);
            taskPers.setUser(persUser);
            taskService.save(taskPers);
            System.out.print(taskPers.getId());

        }
    }

    @Override
    public void run(String... args) throws Exception {
       // load();
    }
}
