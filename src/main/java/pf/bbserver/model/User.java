package pf.bbserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@javax.persistence.Entity
@Getter
@Setter
public class User extends EntityWithID {

    @NotEmpty @NotBlank @NotNull
    String role;

    @Column(length = 20, nullable = false, unique = true)
    String name;

    @Column(length = 100, nullable = false, unique = true)
    String email;

    @Setter(AccessLevel.NONE)
    @Column(length = 100, nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String passwordHash;

    @NotNull @NotBlank @NotEmpty
    @Column(length = 50)
    String forename;

    @NotNull @NotBlank @NotEmpty
    @Column(length = 50)
    String lastname;

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return String.format(this.getClass().getName() + "[id=%d, name='%s']", id, name);
    }

    public void setPasswordHash(String encode) {
        this.passwordHash = encode;
    }
}
