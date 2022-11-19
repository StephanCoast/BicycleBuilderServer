package shop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity @Getter @Setter
public class Booking extends IdentifiedEntity {

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Europe/Berlin")
    Date date = new Date();

    @Column(name = "bookingprice", nullable = false)
    Integer bookingPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties({"description"})
    Product product;

    @Override
    public String toString() {
        return String.format("Booking[id=%d, user='%s', product='%s']", id, user.name, product.title);
    }
}
