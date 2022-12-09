package pf.bbserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pf.bbserver.model.Configuration;

import java.util.Date;
import java.util.List;

public interface ConfigurationRepo extends JpaRepository<Configuration, Long> {

    // von JpaRepository geerbte Methoden:
    // save(), findOne(), findById(), findAll(), count(), delete(), deleteById()

    List<Configuration> findByDateLastChanged(Date dateLastChanged);

}