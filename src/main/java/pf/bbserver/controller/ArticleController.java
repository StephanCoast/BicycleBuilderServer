package pf.bbserver.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pf.bbserver.model.Article;
import pf.bbserver.repository.ArticleRepo;


//@CrossOrigin(origins = "http://localhost:8081")
//@RequestMapping("/api")
@RestController
public class ArticleController {
    final
    ArticleRepo articleRepo;

    public ArticleController(ArticleRepo articleRepo) {
        this.articleRepo = articleRepo;
    }

    @GetMapping("/articles")
    public ResponseEntity<List<Article>> getAll() {
        try {
            List<Article> articles;
            articles = articleRepo.findAll();

            if (articles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(articles, HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/articles/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable("id") long id) {
        Optional<Article> article = articleRepo.findById(id);

        if (article.isPresent()) {
            return new ResponseEntity<>(article.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}