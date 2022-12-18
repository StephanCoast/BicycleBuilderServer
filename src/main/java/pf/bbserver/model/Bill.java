package pf.bbserver.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@javax.persistence.Entity
@Getter @Setter
public class Bill extends EntityWithID {

	@OneToOne
    OrderClass orderClass;

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
