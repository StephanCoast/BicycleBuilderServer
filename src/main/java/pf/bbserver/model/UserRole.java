package pf.bbserver.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@javax.persistence.Entity
@Getter @Setter
public class UserRole extends EntityWithID {

	@Length(max = 50) @NotBlank @NotEmpty @NotNull
	String name;

	@Override
	public String toString() {
		return String.format(this.getClass().getName() + "[id=%d, name='%s']", id, name);
	}
}
