package shop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import shop.model.Booking;
import shop.model.projection.BookingInlineProduct;

@RepositoryRestResource(excerptProjection = BookingInlineProduct.class)
public interface BookingRepository extends CrudRepository<Booking, Integer> {
}
