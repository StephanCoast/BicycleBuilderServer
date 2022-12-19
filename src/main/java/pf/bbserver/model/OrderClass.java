package pf.bbserver.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@javax.persistence.Entity
@Getter @Setter
public class OrderClass extends EntityWithID {

	@NotNull
	@OneToOne
	@JoinColumn
	Configuration configuration;

	@ManyToOne @NotNull
	Customer customer;

	@NotNull
	@Column
	float priceTotal;

	@OneToOne
	Bill bill;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Europe/Berlin")
	@Column(nullable = false)
	Date dateCreated;

	@PrePersist
	protected void onCreate() {
		dateCreated = new Date();
	}

	@Override
	public String toString() {
		return String.format(this.getClass().getName() + "[id=%d, name='%s']", id);
	}
}
