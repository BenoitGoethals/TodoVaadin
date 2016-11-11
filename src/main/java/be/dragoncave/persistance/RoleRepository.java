package be.dragoncave.persistance;

import be.dragoncave.domain.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by benoit on 11/11/2016.
 */
public interface RoleRepository extends CrudRepository<Role,Integer> {
}
