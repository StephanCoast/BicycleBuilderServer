package pf.bbserver.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.CascadeType.*;

@javax.persistence.Entity
@Getter @Setter
public class Configuration extends EntityWithID {


    @NotEmpty @NotBlank @NotNull
    String status;

    @NotNull
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Europe/Berlin")
    Date dateCreated = new Date();

    @NotNull
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Europe/Berlin")
    Date dateLastChanged = new Date();

    @NotNull
    @Column
    boolean writeAccess = true;

    @ManyToOne @NotNull
    @JoinColumn
    User user;

    @OneToOne
    @JoinColumn
    OrderClass orderClass;

    // Set is more efficient than list
    @ManyToMany
    @JoinTable
    Set<Article> articles = new HashSet<>();

    @Override
    public String toString() {
        return String.format(this.getClass().getName() + "[datum='%s', id=%d, benutzer='%s']", dateLastChanged, id, user.name);
    }
}
