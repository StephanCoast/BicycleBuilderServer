package pf.bbserver.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@javax.persistence.Entity
@Getter @Setter
public class Konfiguration extends EntityMitID {

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Europe/Berlin")
    Date datum = new Date();

    enum Status {
        ENTWURF,
        ABGESCHLOSSEN,
        EINKAUF,
        STORNO
    }
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column
    Status status;

    @NotNull
    @Column
    float gesamtpreis;

    @Column
    boolean schreibschutz;

    @ManyToOne
    @JoinColumn
    Benutzer benutzer;

    @OneToOne
    @JoinColumn
    Auftrag auftrag;

    @ManyToOne
    @JoinColumn
    Rahmen rahmen;

    @ManyToOne
    @JoinColumn
    Reifen reifen;

    @ManyToOne
    @JoinColumn
    Laufrad laufrad;

    @ManyToOne
    @JoinColumn
    Bremse bremse;

    @ManyToOne
    @JoinColumn
    Schaltung schaltung;

    @ManyToOne
    @JoinColumn
    Innenlager innenlager;

    @ManyToOne
    @JoinColumn
    Kurbel kurbel;

    @ManyToOne
    @JoinColumn
    Pedale pedale;


    @ManyToOne
    @JoinColumn
    Gabel gabel;

    @ManyToOne
    @JoinColumn
    Steuersatz steuersatz;

    @ManyToOne
    @JoinColumn
    Vorbau vorbau;

    @ManyToOne
    @JoinColumn
    Lenker lenker;

    @ManyToMany
    @JoinColumn
    List<Zubehoer> zubehoer;

    @Override
    public String toString() {
        return String.format(this.getClass().getName() + "[datum='%s', id=%d, benutzer='%s']", datum, id, benutzer.name);
    }
}
