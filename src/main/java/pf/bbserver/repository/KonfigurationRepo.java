package pf.bbserver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pf.bbserver.model.Konfiguration;
import pf.bbserver.model.projection.KonfigurationAnzeigen;

@RepositoryRestResource(excerptProjection = KonfigurationAnzeigen.class)
public interface KonfigurationRepo extends CrudRepository<Konfiguration, Integer> {
}
