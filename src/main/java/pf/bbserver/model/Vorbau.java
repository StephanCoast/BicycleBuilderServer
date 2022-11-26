package pf.bbserver.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity @Getter @Setter
public class Vorbau extends Artikel {


	enum Vorbautyp {
		KLASSISCH,
		AHEAD,
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column
	Vorbautyp vorbautyp;

	@NotBlank @NotEmpty @NotNull
	@Column
	float gabelschaftDurchmesserMm;

	@NotBlank @NotEmpty @NotNull
	@Column
	float lenkerklemmungDurchmesserMm;

	@Override
	public String toString() {
		return String.format(this.getClass().getName() + "[id=%d, name='%s', typ='%s', vorbautyp='%s', gabelschaftDurchmesser='%s', lenkerklemmungDurchmesser='%s']", id, name, artikeltyp, vorbautyp, gabelschaftDurchmesserMm, lenkerklemmungDurchmesserMm);
	}
}
