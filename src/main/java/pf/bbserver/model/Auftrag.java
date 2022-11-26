package pf.bbserver.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@javax.persistence.Entity
@Getter @Setter
public class Auftrag extends EntityMitID {

	@OneToOne
	Auftrag auftrag;

	@OneToOne
	Rechnung rechnung;

	@ManyToOne
	Kunde kunde;

	@Override
	public String toString() {
		return String.format(this.getClass().getName() + "[id=%d, name='%s']", id);
	}
}
