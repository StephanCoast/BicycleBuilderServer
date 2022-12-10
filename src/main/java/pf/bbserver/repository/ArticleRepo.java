package pf.bbserver.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import pf.bbserver.model.Article;

public interface ArticleRepo extends CrudRepository<Article, Integer> {

    // von JpaRepository geerbte Methoden:
    // save(), findOne(), findById(), findAll(), count(), delete(), deleteById()

    List<Article> findByDescriptionContaining(String title);

}