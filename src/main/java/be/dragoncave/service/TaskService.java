package be.dragoncave.service;

import be.dragoncave.domain.Task;

import java.util.List;

/**
 * Created by benoit on 01/11/2016.
 */
public interface TaskService {
    Task task(String taskNbr);

    Task task(int id);

    List<Task> tasks();

    Task save(Task task);

    void delete(Task selected);
}
