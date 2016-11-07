package be.dragoncave.persistance;

import be.dragoncave.domain.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by benoit on 02/11/2016.
 */
public interface CountryRepository extends CrudRepository<Country, Integer> {
    Page<Country> findAll(Pageable pageable);


}
