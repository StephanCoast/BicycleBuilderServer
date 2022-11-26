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
public class Bremse extends Artikel {

	enum Bremsentyp {
		SCHEIBENBREMSE,
		FELGENBREMSE,
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column
	Bremsentyp bremsentyp;

	@Positive
	@Column(nullable = false)
	int bremsscheibeDurchmesserMmMax;

	@Override
	public String toString() {
		return String.format(this.getClass().getName() + "[id=%d, name='%s', typ='%s', bremsentyp='%s']", id, name, artikeltyp, bremsentyp);
	}
}
