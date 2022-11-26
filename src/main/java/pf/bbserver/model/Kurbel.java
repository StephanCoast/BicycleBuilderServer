package pf.bbserver.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Entity @Getter @Setter
public class Kurbel extends Artikel {


	enum InnenlagerWellentyp {
		VIERKANT,
		VIELZAHN,
		KURBELWELLE,
	}
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column
	InnenlagerWellentyp innenlagerWellentyp;


	enum Pedalgewindetyp {
		TPI,
		OPC,
	}
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column
	Pedalgewindetyp pedalgewindetyp;


	@NotNull
	@Positive
	@Column
	int kurbellaengeMM;


	@Override
	public String toString() {
		return String.format(this.getClass().getName() + "[id=%d, name='%s', typ='%s', pedalgewindetyp='%s', wellentyp='%s', kurbellaengeMM='%s']", id, name, artikeltyp, pedalgewindetyp, innenlagerWellentyp, kurbellaengeMM);
	}
}
