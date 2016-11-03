package be.dragoncave.service;

import be.dragoncave.domain.Task;
import be.dragoncave.domain.TaskStatus;
import be.dragoncave.domain.TaskType;
import be.dragoncave.persistance.TaskReprository;
import be.dragoncave.service.TaskService;
import be.dragoncave.service.TaskServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

/**
 * Created by benoit on 01/11/2016.
 */
/*
@RunWith(SpringRunner.class)
@SpringBootTest
// Enable JMX so we can test the MBeans (you can't do this in a properties file)
@TestPropertySource(properties = { "spring.jmx.enabled:true",
        "spring.datasource.jmx-enabled:true" })*/
@RunWith(SpringRunner.class)

public class TaskServiceTests {

    @MockBean
    private TaskReprository taskReprository;


    @Test
    @Rollback(value = true)
    public void setup() {

        LocalDateTime timePoint = LocalDateTime.now();     // The current date and time

        LocalDateTime endDate = LocalDateTime.now().plusMonths(2);
        Task task = new Task("ffsdf", timePoint, endDate, TaskType.PRIVATE, TaskStatus.RUNNING);

        when(this.taskReprository.findOne(1)).thenReturn(task);
        TaskService taskService = new TaskServiceImpl(taskReprository);
        assertThat(taskService.task(1), is(task));
    }


}
