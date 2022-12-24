package pf.bbserver.repository;

import org.springframework.data.repository.CrudRepository;
import pf.bbserver.model.Bill;

public interface BillRepo extends CrudRepository<Bill, Integer> {

    // von CrudRepository geerbte Methoden:
    // save(), findOne(), findById(), findAll(), count(), delete(), deleteById()


}