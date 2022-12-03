package pf.bbserver.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pf.bbserver.model.Article;

@Repository
public interface ArticleRepo extends PagingAndSortingRepository<Article, Integer> {

    // Query methods are documented at https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods

//    @RestResource(path = "byName")
//    List<Artikel> findByName(@Param(value = "name") String name);

//    @RestResource(path = "inDescription")
//    List<Artikel> findByDescriptionContainingIgnoreCase(@Param(value = "pattern") String pattern);
//
//    @RestResource(path = "maxPrice")
//    List<Artikel> findByPriceLessThanEqual(@Param(value = "price") Integer price);
}
