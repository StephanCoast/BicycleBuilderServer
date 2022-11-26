package pf.bbserver.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity @Getter @Setter
public class Lenker extends Artikel {


	@NotBlank @NotEmpty @NotNull
	@Column
	float lenkerklemmungDurchmesserMm;

	@Override
	public String toString() {
		return String.format(this.getClass().getName() + "[id=%d, name='%s', typ='%s', lenkerklemmungDurchmesser='%s']", id, name, artikeltyp, lenkerklemmungDurchmesserMm);
	}
}
