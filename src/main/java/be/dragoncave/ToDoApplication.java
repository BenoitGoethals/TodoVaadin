package be.dragoncave;

import be.dragoncave.domain.*;
import be.dragoncave.persistance.CountryRepository;
import be.dragoncave.persistance.RoleRepository;
import be.dragoncave.persistance.UserDetailRepository;
import be.dragoncave.service.SecurityService;
import be.dragoncave.service.TaskService;
import be.dragoncave.service.UserService;
import be.dragoncave.util.CountryConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextListener;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication()
//@Profile("prod")
public class ToDoApplication implements CommandLineRunner {

    @Autowired
    private TaskService taskService;
    @Autowired

    private CountryConverter countryConverter;
    @Autowired

    private UserService userService;

    @Autowired
    private SecurityService securityService;


    @Autowired
    private CountryRepository countryRepository;

    public static void main(String[] args) {
        SpringApplication.run(ToDoApplication.class, args);

    }

    // @Bean()
    public CommandLineRunner loadData() {

        Role role=new Role("admin");


        List<Country> countries = countryConverter.parse("src/main/resources/countries.xml");

        countryRepository.save(countries);


        Role role1=new Role("user");

        Role role2= securityService.addRole(role);
        securityService.addRole(role1);

        UserDetail user=new UserDetail("benoit","ranger14",true);
        User persUsers = new User("benoit" , "sddssddsq" , "dqd" , "dsqssd", "9899", "dfsdf", countryRepository.findOne(1), LocalDateTime.now().minusYears(50));
        UserDetail userDetailbenoit=new UserDetail("benoit","ranger14",true);
        userDetailbenoit.setRole(role);
        persUsers.setUserDetail(userDetailbenoit);

        System.out.println(countryRepository.count());
        for (int i = 1; i <= 200; i++) {
            User persUser = new User("user" + i, "sdd" + i, "dqd" + i, "dsqd" + i, "9899", "dfsdf", countryRepository.findOne(i), LocalDateTime.now().minusYears(50));
            UserDetail userDetail = new UserDetail("user" + i, "ranger14", true);
            userDetail.setRole(role2);
            persUser.setUserDetail(userDetail);

            userService.save(persUser);
            Task taskPers = new Task("ffsdf" + i, LocalDateTime.now().plusMonths(i), LocalDateTime.now().plusMonths(i).plusDays(20), TaskType.PRIVATE, TaskStatus.RUNNING);
            taskPers.setUser(persUser);
            taskService.save(taskPers);
            System.out.print(taskPers.getId());

        }
        System.out.println("hallo");
        return null;
    }


    @Bean
    @ConditionalOnMissingBean(RequestContextListener.class)
    public RequestContextListener requestContextListener() {

        return new RequestContextListener();
    }

    @Override
    public void run(String... args) throws Exception {
        // load();
    }


    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
