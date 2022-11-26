package pf.bbserver.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@javax.persistence.Entity
@Getter @Setter
public class EigenschaftFrei extends EntityMitID {


	@NotBlank @NotEmpty @NotNull
	@Column(length = 50)
	String name;

	@NotNull
	@ManyToOne
	@JoinColumn
	Datentyp datentyp;

	@NotNull
	@ManyToOne
	@JoinColumn
	Vergleichstyp vergleichstyp;

	@Length(max = 50)
	@NotBlank
	@NotEmpty
	@NotNull
	@Column
	String wert;


	@Override
	public String toString() {
		return String.format(this.getClass().getName() + "[id=%d, name='%s', typ='%s']", id, name);
	}
}
