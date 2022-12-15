package pf.bbserver.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@javax.persistence.Entity
@Getter @Setter
public class OrderClass extends EntityWithID {

	@NotNull
	@OneToOne
    Configuration configuration;

	@ManyToOne @NotNull
	Customer customer;

	@NotNull
	@Column
	float priceTotal;

	@OneToOne
	Bill bill;

	@NotNull
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Europe/Berlin")
	Date dateCreated = new Date();

	@Override
	public String toString() {
		return String.format(this.getClass().getName() + "[id=%d, name='%s']", id);
	}
}
