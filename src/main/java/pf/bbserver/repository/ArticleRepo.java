package pf.bbserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import pf.bbserver.model.Article;

public interface ArticleRepo extends JpaRepository<Article, Long> {

    // von JpaRepository geerbte Methoden:
    // save(), findOne(), findById(), findAll(), count(), delete(), deleteById()

    List<Article> findByDescriptionContaining(String title);

}