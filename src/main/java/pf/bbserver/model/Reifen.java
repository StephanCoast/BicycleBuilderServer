package pf.bbserver.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Positive;

@Entity @Getter @Setter
public class Reifen extends Artikel {

	@Positive
	@Column(nullable = false)
	int reifenbreiteMm;

	@Positive
	@Column(nullable = false)
	int reifendurchmesserMm;


	@Override
	public String toString() {
		return String.format(this.getClass().getName() + "[id=%d, title='%s', typ='%s', breiteMm='%s', durchmesserMm='%s']", id, name, artikeltyp, reifenbreiteMm, reifendurchmesserMm);
	}
}
