package pf.bbserver.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity @Getter @Setter
public class Steuersatz extends Artikel {


	@NotBlank @NotEmpty @NotNull
	@Column
	float steuersatzDurchmesserMm;

	@NotBlank @NotEmpty @NotNull
	@Column
	float gabelschaftDurchmesserMm;

	@Override
	public String toString() {
		return String.format(this.getClass().getName() + "[id=%d, name='%s', typ='%s', steuersatzDurchmesserMm='%s', gabelschaftDurchmesserMm='%s']", id, name, artikeltyp, steuersatzDurchmesserMm, gabelschaftDurchmesserMm);
	}
}
