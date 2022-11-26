package pf.bbserver.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Positive;

@Entity @Getter @Setter
public class Laufrad extends Artikel {

	@Positive
	@Column
	int einbaubreiteMm;

	@Positive
	@Column
	int reifenbreiteMmMin;

	@Positive
	@Column
	int reifenbreiteMmMax;

	@Positive
	@Column
	int reifendurchmesserMm;


	@Override
	public String toString() {
		return String.format(this.getClass().getName() + "[id=%d, title='%s', typ='%s', einbaubreiteMm='%s', reifenbreiteMinMm='%s', reifenbreiteMaxMm='%s', reifendurchmesserMm='%s']", id, name, artikeltyp, einbaubreiteMm, reifenbreiteMmMin, reifenbreiteMmMax, reifendurchmesserMm);
	}
}
