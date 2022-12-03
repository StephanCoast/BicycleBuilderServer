package pf.bbserver.model.projection;

import org.springframework.data.rest.core.config.Projection;
import pf.bbserver.model.Configuration;
import pf.bbserver.model.Article;

@Projection(name = "KonfigurationAnzeigen", types = {Configuration.class})
public interface KonfigurationAnzeigen {
    String getDatum();
    float getGesamtpreis();
    Article[] getArtikelliste();
}