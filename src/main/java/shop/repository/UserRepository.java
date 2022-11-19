package shop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import shop.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    
    User findByName(@Param(value = "name") String name);

    @Override
    @RestResource(exported = false)
    Optional<User> findById(Integer id);

    @Override
    @RestResource(exported = false)
    void delete(User user);
}
