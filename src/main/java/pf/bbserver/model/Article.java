package pf.bbserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


@javax.persistence.Entity
@Getter @Setter
public class Article extends EntityWithID {

	@NotEmpty @NotBlank @NotNull
	String type;

	@NotBlank @NotEmpty @NotNull
	@Column(length = 100)
	String name;


	@NotBlank @NotEmpty @NotNull
	@Column(length = 100)
	String producer;

	@Column(length = 200)
	String description;

	@Column(length = 100)
	String characteristic;

	@Column
	int value1;

	@Column
	int value2;

	@Column
	@NotNull
	float price;

	@NotBlank @NotEmpty @NotNull
	@Column(length = 10)
	String hexColor;

//	@ManyToMany(mappedBy = "articles") // give Ownership of relation to Configuration
//	@JsonIgnore
//	List<Article> articles;

	@Override
	public String toString() {
		return String.format(this.getClass().getName() + "[id=%d, name='%s', typ='%s', preis='%s', farbe='%s']", id, name, type, price, hexColor);
	}
}
