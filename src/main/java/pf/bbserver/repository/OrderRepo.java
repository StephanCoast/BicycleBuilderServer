package pf.bbserver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pf.bbserver.model.OrderClass;

@RepositoryRestResource(collectionResourceRel = "orders", path = "orders")
public interface OrderRepo extends CrudRepository<OrderClass, Integer> {

    // von CrudRepository geerbte Methoden:
    // save(), findOne(), findById(), findAll(), count(), delete(), deleteById()


}