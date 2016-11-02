package be.dragoncave;

import be.dragoncave.domain.Task;
import be.dragoncave.domain.TaskStatus;
import be.dragoncave.domain.TaskType;
import be.dragoncave.persistance.TaskReprository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by benoit on 01/11/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class TaskReprositoryTests {

    @Autowired
    private TaskReprository taskReprository;

    @Test
    public void testExample() throws Exception {

        LocalDateTime timePoint = LocalDateTime.now();     // The current date and time

        LocalDateTime endDate = LocalDateTime.now().plusMonths(2);


        Task taskPers = new Task("ffsdf", timePoint, endDate, TaskType.PRIVATE, TaskStatus.RUNNING);
        this.taskReprository.save(taskPers);
        Task task = taskReprository.findAll().iterator().next();
        assertThat(task.getDescription()).isEqualTo("ffsdf");
        //  User user = this.repository.findByUsername("sboot");
        // assertThat(user.getUsername()).isEqualTo("sboot");
        //  assertThat(user.getVin()).isEqualTo("1234");
    }
}
