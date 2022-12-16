package pf.bbserver.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pf.bbserver.model.Configuration;
import pf.bbserver.repository.ConfigurationRepo;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@RestController
public class ConfigurationController {

    final ConfigurationRepo configurationRepo;

    final static String baseURL= "http://localhost:8080/configurations/";

    public ConfigurationController(ConfigurationRepo configurationRepo) {
        this.configurationRepo = configurationRepo;
    }

    @GetMapping("/configurations")
    public ResponseEntity<List<Configuration>> getAllConfigurations() {
        try {

            List<Configuration> configurations = new ArrayList<>((Collection) configurationRepo.findAll());

            if (configurations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(configurations, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/configurations/{id}")
    public ResponseEntity<Configuration> getConfigurationById(@PathVariable("id") int id) {
        Optional<Configuration> configurationData = configurationRepo.findById(id);

        if (configurationData.isPresent()) {
            return new ResponseEntity<>(configurationData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/configurations")
    public ResponseEntity<Configuration> createTutorial(@RequestBody Configuration configuration) {
        try {
            Configuration newConfig = configurationRepo.save(configuration);

            //Return URI somehow necessary for succesful completion of Post task
            URI location = URI.create(baseURL + newConfig.getId());
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setLocation(location);

            return new ResponseEntity<>(newConfig, responseHeaders, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
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
    @DeleteMapping("/configurations/{id}")
    public ResponseEntity<HttpStatus> deleteConfiguration(@PathVariable("id") int id) {
        try {
            configurationRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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