package pf.bbserver.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;



@javax.persistence.Entity
@Getter
@Setter
public class Customer extends EntityWithID {

    @Column(length = 100, nullable = false, unique = true)
    String email;

    @NotNull @NotBlank @NotEmpty
    @Column(length = 50)
    String forename;

    @NotNull @NotBlank @NotEmpty
    @Column(length = 50)
    String lastname;

    @NotNull @NotBlank @NotEmpty
    @Column(length = 100)
    String street;

    @NotNull
    @Column
    int houseNumber;

    @NotNull @NotBlank @NotEmpty
    @Column(length = 10)
    String zipCode;

    @NotNull @NotBlank @NotEmpty
    @Column(length = 100)
    String city;

    @Override
    public String toString() {
        return String.format(this.getClass().getName() + "[id=%d, vorname='%s', nachname='%s', email='%s']", id, forename, lastname, email);
    }
}
