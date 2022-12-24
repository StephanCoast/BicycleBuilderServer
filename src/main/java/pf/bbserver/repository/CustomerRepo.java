package pf.bbserver.repository;

import org.springframework.data.repository.CrudRepository;
import pf.bbserver.model.Customer;


public interface CustomerRepo extends CrudRepository<Customer, Integer> {

    // von CrudRepository geerbte Methoden:
    // save(), findOne(), findById(), findAll(), count(), delete(), deleteById()


}