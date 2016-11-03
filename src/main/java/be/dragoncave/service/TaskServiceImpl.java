package be.dragoncave.service;

import be.dragoncave.domain.Task;
import be.dragoncave.persistance.TaskReprository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by benoit on 01/11/2016.
 */
@Service
public class TaskServiceImpl implements TaskService {


    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Autowired
    private TaskReprository taskReprository;

    public TaskServiceImpl(TaskReprository taskReprository) {
        this.taskReprository = taskReprository;
    }


    @Override
    public Task task(String taskNbr) {

        return taskReprository.findBynbrTask(taskNbr);
    }

    @Override
    public Task task(int id) {

        return taskReprository.findOne(id);
    }

    @Override
    public List<Task> tasks() {
        return (List<Task>) taskReprository.findAll();
    }

    @Override
    public Task save(Task task) {
       Task task1= taskReprository.save(task);
        logger.info("Saved task :"+task1);
        return task1;
    }
}
