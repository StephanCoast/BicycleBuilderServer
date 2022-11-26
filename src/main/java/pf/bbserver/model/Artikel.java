package pf.bbserver.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;



@MappedSuperclass @Getter @Setter
public abstract class Artikel extends EntityMitID {


	@NotBlank @NotEmpty @NotNull
	@Column(length = 50)
	String name;

	@Column
	@NotNull
	float preis;

	@NotBlank @NotEmpty @NotNull
	@Column(length = 10)
	String farbe;

	@NotNull
	@ManyToOne
	@JoinColumn
	Artikeltyp artikeltyp;


	@Override
	public String toString() {
		return String.format(this.getClass().getName() + "[id=%d, name='%s', typ='%s', preis='%s', farbe='%s']", id, name, artikeltyp, preis, farbe);
	}
}
