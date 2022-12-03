package pf.bbserver.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.OneToOne;

@javax.persistence.Entity
@Getter @Setter
public class Bill extends EntityWithID {

	@OneToOne
    OrderClass orderClass;

	@Override
	public String toString() {
		return String.format(this.getClass().getName() + "[id=%d, name='%s']", id);
	}
}
