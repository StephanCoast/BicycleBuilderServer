package shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity @Getter @Setter
public class Product extends IdentifiedEntity {

	@Column(length = 50, nullable = false)
	String title;

	@Lob
	String description;

	@Lob
	@JsonIgnore
	byte[] image;

	@Column(nullable = false)
	Integer price;

	Boolean instock;

	@Override
	public String toString() {
		return String.format("Product[id=%d, title='%s']", id, title);
	}
}
