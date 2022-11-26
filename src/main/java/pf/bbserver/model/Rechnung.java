package pf.bbserver.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.OneToOne;

@javax.persistence.Entity
@Getter @Setter
public class Rechnung extends EntityMitID {

	@OneToOne
    Auftrag auftrag;

	@Override
	public String toString() {
		return String.format(this.getClass().getName() + "[id=%d, name='%s']", id);
	}
}
