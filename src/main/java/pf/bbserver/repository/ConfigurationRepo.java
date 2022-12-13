package pf.bbserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import pf.bbserver.model.Article;
import pf.bbserver.model.Configuration;

import java.util.Date;
import java.util.List;

public interface ConfigurationRepo extends CrudRepository<Configuration, Integer> {

    // von CrudRepository geerbte Methoden:
    // save(), findOne(), findById(), findAll(), count(), delete(), deleteById()

    List<Configuration> findByDateLastChanged(Date dateLastChanged);

}