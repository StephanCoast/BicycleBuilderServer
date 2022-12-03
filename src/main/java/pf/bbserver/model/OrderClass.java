package pf.bbserver.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@javax.persistence.Entity
@Getter @Setter
public class OrderClass extends EntityWithID {

	@OneToOne
    OrderClass orderClass;

	@OneToOne
	Bill bill;

	@ManyToOne
	Customer customer;

	@Override
	public String toString() {
		return String.format(this.getClass().getName() + "[id=%d, name='%s']", id);
	}
}
