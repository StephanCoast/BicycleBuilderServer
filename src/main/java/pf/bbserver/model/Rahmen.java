package pf.bbserver.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.*;

@Entity @Getter @Setter
public class Rahmen extends Artikel {

	@NotBlank @NotEmpty @NotNull
	@Column(length = 50)
	String rahmentyp;

	@Range(min = 18, max = 70) @NotNull
	@Column
	int hoeheCM;

	@NotBlank @NotEmpty @NotNull
	@Column(length = 50)
	String bremsentyp;

	@NotBlank @NotEmpty @NotNull
	@Column(length = 50)
	String schaltungstyp;

	@NotBlank @NotEmpty @NotNull
	@Column(length = 50)
	String reifendurchmesser;

	@NotBlank @NotEmpty @NotNull
	@Column(length = 50)
	String tretlagertyp;

	@NotBlank @NotEmpty @NotNull
	@Column
	float tretlagerDurchmesserMm;

	@NotBlank @NotEmpty @NotNull
	@Column
	float steuersatzDurchmesserMm;

	@NotBlank @NotEmpty @NotNull
	@Column
	float sattelstuetzeDurchmesserMm;

	@Override
	public String toString() {
		return String.format(this.getClass().getName() + "[id=%d, title='%s', typ='%s', rahmentyp='%s', hoeheCM='%s', farbe='%s']", id, name, artikeltyp, rahmentyp, hoeheCM, farbe);
	}
}
