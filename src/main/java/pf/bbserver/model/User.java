package pf.bbserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@javax.persistence.Entity
@Getter
@Setter
public class User extends EntityWithID {

    @Column(length = 20, nullable = false, unique = true)
    String name;

    @Column(length = 100, nullable = false, unique = true)
    String email;

    @Column(length = 100, nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String passwordHash;

    @NotNull @NotBlank @NotEmpty
    @Column(length = 50)
    String forename;

    @NotNull @NotBlank @NotEmpty
    @Column(length = 50)
    String lastname;

    @NotNull
    @ManyToOne
    @JoinColumn
    UserRole userRole;

    @OneToMany
    List<Configuration> configurations;

    @Override
    public String toString() {
        return String.format(this.getClass().getName() + "[id=%d, name='%s']", id, name);
    }
}
