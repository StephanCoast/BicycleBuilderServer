package pf.bbserver.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;


@Entity @Getter @Setter
public class Schaltung extends Artikel {

	enum Schaltungstyp {
		NABENSCHALTUNG,
		KETTENSCHALTUNG,
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column
	Schaltungstyp schaltungstyp;

	@Column
	int ganganzahl;

	@Override
	public String toString() {
		return String.format(this.getClass().getName() + "[id=%d, name='%s', typ='%s', schaltungstyp='%s', ganganzahl='%s']", id, name, artikeltyp, schaltungstyp, ganganzahl);
	}
}
