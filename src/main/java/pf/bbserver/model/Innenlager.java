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
public class Innenlager extends Artikel {

	enum Innenlagertyp {
		BSA,
		ITA,
		BB30,
	}

	enum InnenlagerWellentyp {
		VIERKANT,
		VIELZAHN,
		KURBELWELLE,
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column
	Innenlagertyp innenlagertyp;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column
	InnenlagerWellentyp innenlagerWellentyp;

	@NotNull
	@Positive
	@Column
	float innenlagerDurchmesserMm;



	@Override
	public String toString() {
		return String.format(this.getClass().getName() + "[id=%d, name='%s', typ='%s', innenlagertyp='%s', wellentyp='%s', innenlagerdurchmesserMM='%s']", id, name, artikeltyp, innenlagertyp, innenlagerWellentyp, innenlagerDurchmesserMm);
	}
}
