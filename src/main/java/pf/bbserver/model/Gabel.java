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
import javax.validation.constraints.Positive;


@Entity @Getter @Setter
public class Gabel extends Artikel {


	enum Gabeltyp {
		STARRGABEL,
		FEDERGABEL,
	}

	enum Vorbautyp {
		KLASSISCH,
		AHEAD,
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column
	Gabeltyp gabeltyp;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column
	Vorbautyp vorbautyp;

	@NotBlank @NotEmpty @NotNull
	@Column
	float gabelschaftDurchmesserMm;
	
	@Positive
	@Column(nullable = false)
	int reifenbreiteMmMax;

	@Positive
	@Column(nullable = false)
	int bremsscheibeDurchmesserMmMax;

	@Override
	public String toString() {
		return String.format(this.getClass().getName() + "[id=%d, name='%s', typ='%s', gabeltyp='%s', vorbautyp='%s', pedalgewindetyp='%s']", id, name, artikeltyp, gabeltyp, vorbautyp, gabelschaftDurchmesserMm);
	}
}
