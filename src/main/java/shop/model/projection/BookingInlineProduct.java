package shop.model.projection;

import org.springframework.data.rest.core.config.Projection;
import shop.model.Booking;
import shop.model.Product;

@Projection(name = "inlineProduct", types = {Booking.class})
public interface BookingInlineProduct {
    String getDate();
    int getBookingPrice();
    Product getProduct();
}