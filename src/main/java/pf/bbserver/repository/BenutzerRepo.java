package pf.bbserver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import pf.bbserver.model.Benutzer;

import java.util.Optional;

@Repository
public interface BenutzerRepo extends CrudRepository<Benutzer, Integer> {
    
    Benutzer findByName(@Param(value = "name") String name);

    @Override
    @RestResource(exported = false)
    Optional<Benutzer> findById(Integer id);

    @Override
    @RestResource(exported = false)
    void delete(Benutzer benutzer);
}
