package pf.bbserver.repository;

import org.springframework.data.repository.CrudRepository;
import pf.bbserver.model.Customer;

import java.util.Date;
import java.util.List;

public interface CustomerRepo extends CrudRepository<Customer, Integer> {

    // von CrudRepository geerbte Methoden:
    // save(), findOne(), findById(), findAll(), count(), delete(), deleteById()


}