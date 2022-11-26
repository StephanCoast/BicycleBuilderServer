package pf.bbserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@javax.persistence.Entity
@Getter
@Setter
public class Benutzer extends EntityMitID {

    @Column(length = 20, nullable = false, unique = true)
    String name;

    @Column(length = 100, nullable = false, unique = true)
    String email;

    @Column(name = "passwort_hash", length = 100, nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String passwortHash;

    @Column(length = 50)
    String vorname;

    @Column(length = 50)
    String nachname;

    enum Rolle {
        ADMIN,
        BERATER,
    }
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column
    Rolle rolle;


    @OneToMany(mappedBy = "benutzer")
    @JsonIgnoreProperties({"user"})
    List<Konfiguration> configurations;

    @Override
    public String toString() {
        return String.format(this.getClass().getName() + "[id=%d, name='%s']", id, name);
    }
}
