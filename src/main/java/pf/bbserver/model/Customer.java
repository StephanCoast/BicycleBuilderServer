package pf.bbserver.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;


@javax.persistence.Entity
@Getter
@Setter
public class Customer extends EntityWithID {

    @Column(length = 100, nullable = false, unique = true)
    String email;

    @Column(length = 50)
    String vorname;

    @Column(length = 50)
    String nachname;

    @Column(length = 50)
    String strasse;

    @Column(length = 50)
    String hausnummer;

    @Column(length = 50)
    String postleitzahl;

    @Column(length = 50)
    String ort;

    @Column
    @OneToMany
    List<OrderClass> auftraege;

    @Override
    public String toString() {
        return String.format(this.getClass().getName() + "[id=%d, vorname='%s', nachname='%s', email='%s']", id, vorname, nachname, email);
    }
}
