package be.dragoncave.persistance;

import be.dragoncave.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by benoit on 02/11/2016.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Page<User> findAll(Pageable pageable);

    // Page<Task> findByNameContainingAndCountryContainingAllIgnoringCase(String name,
    //                                                                String country, Pageable pageable);

    User findByUserID(String userID);
}
