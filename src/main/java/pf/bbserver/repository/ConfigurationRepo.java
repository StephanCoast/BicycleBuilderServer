package pf.bbserver.repository;


import org.springframework.data.repository.CrudRepository;
import pf.bbserver.model.Configuration;

public interface ConfigurationRepo extends CrudRepository<Configuration, Integer> {

    // von CrudRepository geerbte Methoden:
    // save(), findOne(), findById(), findAll(), count(), delete(), deleteById()


}