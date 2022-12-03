package pf.bbserver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import pf.bbserver.model.User;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {
    
    User findByName(@Param(value = "name") String name);

    @Override
    @RestResource(exported = false)
    Optional<User> findById(Integer id);

    @Override
    @RestResource(exported = false)
    void delete(User user);
}
