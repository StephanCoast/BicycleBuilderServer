package pf.bbserver.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter @Setter
public class Datentyp extends EntityMitID {

	@NotBlank @NotEmpty @NotNull
	@Column(length = 50)
	String name;

	@Override
	public String toString() {
		return String.format(this.getClass().getName() + "[id=%d, name='%s']", id, name);
	}
}
