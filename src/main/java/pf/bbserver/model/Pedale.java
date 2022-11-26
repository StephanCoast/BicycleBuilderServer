package pf.bbserver.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;


@Entity @Getter @Setter
public class Pedale extends Artikel {

	enum Pedalgewindetyp {
		TPI,
		OPC,
	}
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column
	Pedalgewindetyp pedalgewindetyp;


	@Override
	public String toString() {
		return String.format(this.getClass().getName() + "[id=%d, name='%s', typ='%s', pedalgewindetyp='%s']", id, name, artikeltyp, pedalgewindetyp);
	}
}
