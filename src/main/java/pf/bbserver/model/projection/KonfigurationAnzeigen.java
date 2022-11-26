package pf.bbserver.model.projection;

import org.springframework.data.rest.core.config.Projection;
import pf.bbserver.model.Konfiguration;
import pf.bbserver.model.Artikel;

@Projection(name = "KonfigurationAnzeigen", types = {Konfiguration.class})
public interface KonfigurationAnzeigen {
    String getDatum();
    float getGesamtpreis();
    Artikel[] getArtikelliste();
}