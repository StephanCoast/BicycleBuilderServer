package shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class User extends IdentifiedEntity {

    @Column(length = 20, nullable = false, unique = true)
    String name;

    @Column(length = 100, nullable = false, unique = true)
    String email;

    @Column(name = "password_hash", length = 100, nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String passwordHash;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties({"user"})
    List<Booking> bookings;

    @Override
    public String toString() {
        return String.format("User[id=%d, name='%s']", id, name);
    }
}
