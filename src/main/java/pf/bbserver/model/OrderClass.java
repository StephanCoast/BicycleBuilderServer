package pf.bbserver.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@javax.persistence.Entity
@Getter @Setter
public class OrderClass extends EntityWithID {


	@JsonBackReference(value="order-config")
	@NotNull
	@OneToOne
	Configuration configuration;

//	@JsonBackReference(value="order-customer")
	@ManyToOne @NotNull
	Customer customer;

	@JsonManagedReference
	@OneToOne (mappedBy = "order")
	Bill bill;

	@NotNull
	@Column
	float priceTotal;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss", timezone = "Europe/Berlin")
	@Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@UpdateTimestamp
	Timestamp timestampCreated;

	@Override
	public String toString() {
		return String.format(this.getClass().getName() + "[id=%d]", id);
	}
}
