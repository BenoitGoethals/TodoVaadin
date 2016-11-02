package be.dragoncave.persistance;

import be.dragoncave.domain.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by benoit on 01/11/2016.
 */
@Repository
public interface TaskReprository extends CrudRepository<Task, Integer> {
    Page<Task> findAll(Pageable pageable);

    // Page<Task> findByNameContainingAndCountryContainingAllIgnoringCase(String name,
    //                                                                String country, Pageable pageable);

    Task findBynbrTask(String nbrTask);

}
