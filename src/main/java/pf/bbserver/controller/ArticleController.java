package pf.bbserver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pf.bbserver.model.Article;
import pf.bbserver.repository.ArticleRepo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@RestController
public class ArticleController {

    final ArticleRepo articleRepo;

    public ArticleController(ArticleRepo articleRepo) {
        this.articleRepo = articleRepo;
    }

    @GetMapping("/articles")
    public ResponseEntity<List<Article>> getAllArticles() {
        try {

            ArrayList articles = new ArrayList<>((Collection) articleRepo.findAll());

            if (articles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(articles, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}