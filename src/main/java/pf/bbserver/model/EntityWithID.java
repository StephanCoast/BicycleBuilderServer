package pf.bbserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@MappedSuperclass @Getter @Setter
public abstract class EntityWithID {

    @Id
    @Column
    @JsonProperty
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
}
