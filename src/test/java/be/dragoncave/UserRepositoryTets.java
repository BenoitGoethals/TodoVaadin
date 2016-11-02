package be.dragoncave;

import be.dragoncave.domain.Task;
import be.dragoncave.domain.TaskStatus;
import be.dragoncave.domain.TaskType;
import be.dragoncave.domain.User;
import be.dragoncave.persistance.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by benoit on 02/11/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class UserRepositoryTets {


    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveTask() throws Exception {

        LocalDateTime timePoint = LocalDateTime.now();     // The current date and time

        LocalDateTime endDate = LocalDateTime.now().plusMonths(2);

        //   Arrays.asList(new Country("Belgie"),new Country("Nederland")).parallelStream().forEach(f->taskReprository.save(T));
        User persUser = new User();

        // Task taskPers = new Task("ffsdf", timePoint, endDate, TaskType.PRIVATE, TaskStatus.RUNNING);
        // this.taskReprository.save(taskPers);
        this.userRepository.save(persUser);
        User user = userRepository.findAll().iterator().next();
        //  assertThat(user.getDescription()).isEqualTo("ffsdf");
        //  User user = this.repository.findByUsername("sboot");
        // assertThat(user.getUsername()).isEqualTo("sboot");
        //  assertThat(user.getVin()).isEqualTo("1234");
    }
}
