package shop.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import shop.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

    // Query methods are documented at https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods

    @RestResource(path = "byTitle")
    List<Product> findByTitle(@Param(value = "title") String title);

    @RestResource(path = "inDescription")
    List<Product> findByDescriptionContainingIgnoreCase(@Param(value = "pattern") String pattern);

    @RestResource(path = "maxPrice")
    List<Product> findByPriceLessThanEqual(@Param(value = "price") Integer price);
}
