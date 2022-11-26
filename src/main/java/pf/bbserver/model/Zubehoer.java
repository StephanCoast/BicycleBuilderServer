package pf.bbserver.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;



@Entity @Getter @Setter
public class Zubehoer extends Artikel {

	@Override
	public String toString() {
		return String.format("Pedale[id=%d, name='%s', typ='%s']", id, name, artikeltyp);
	}
}
