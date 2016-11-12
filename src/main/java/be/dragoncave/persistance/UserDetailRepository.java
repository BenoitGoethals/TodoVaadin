package be.dragoncave.persistance;

import be.dragoncave.domain.UserDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by benoit on 11/11/2016.
 */
@Repository
public interface UserDetailRepository extends CrudRepository<UserDetail,Integer> {
    UserDetail findByuserName(String username);
}
