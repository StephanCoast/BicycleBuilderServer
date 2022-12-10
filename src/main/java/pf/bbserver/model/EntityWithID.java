package pf.bbserver.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass @Getter @Setter
public abstract class EntityWithID {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
}
