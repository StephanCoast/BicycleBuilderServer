package pf.bbserver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pf.bbserver.model.Article;
import pf.bbserver.model.Configuration;
import pf.bbserver.repository.ArticleRepo;
import pf.bbserver.repository.ConfigurationRepo;

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

            List<Article> articles = new ArrayList<>((Collection) articleRepo.findAll());

            if (articles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(articles, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/tutorials/{id}")
//    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
//        Optional<Tutorial> tutorialData = tutorialRepository.findById(id);
//
//        if (tutorialData.isPresent()) {
//            return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @PostMapping("/tutorials")
//    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
//        try {
//            Tutorial _tutorial = tutorialRepository
//                    .save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false));
//            return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PutMapping("/tutorials/{id}")
//    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
//        Optional<Tutorial> tutorialData = tutorialRepository.findById(id);
//
//        if (tutorialData.isPresent()) {
//            Tutorial _tutorial = tutorialData.get();
//            _tutorial.setTitle(tutorial.getTitle());
//            _tutorial.setDescription(tutorial.getDescription());
//            _tutorial.setPublished(tutorial.isPublished());
//            return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @DeleteMapping("/tutorials/{id}")
//    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
//        try {
//            tutorialRepository.deleteById(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @DeleteMapping("/tutorials")
//    public ResponseEntity<HttpStatus> deleteAllTutorials() {
//        try {
//            tutorialRepository.deleteAll();
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }
//
//    @GetMapping("/tutorials/published")
//    public ResponseEntity<List<Tutorial>> findByPublished() {
//        try {
//            List<Tutorial> tutorials = tutorialRepository.findByPublished(true);
//
//            if (tutorials.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(tutorials, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}