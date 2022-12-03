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
public class Configuration extends EntityWithID {

    @NotNull
    @ManyToOne
    @JoinColumn
    ConfigurationStatus status;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Europe/Berlin")
    Date dateCreated = new Date();

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Europe/Berlin")
    Date dateLastChanged = new Date();

    @Column
    boolean writeAccess;

    @ManyToOne
    @JoinColumn
    User user;

    @OneToOne
    @JoinColumn
    OrderClass orderClass;

    @ManyToMany
    @JoinColumn
    List<Article> articles;

    @Override
    public String toString() {
        return String.format(this.getClass().getName() + "[datum='%s', id=%d, benutzer='%s']", dateLastChanged, id, user.name);
    }
}
