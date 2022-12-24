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
    public ResponseEntity<Configuration> createConfiguration(@RequestBody Configuration configuration) {
        try {
            Configuration newConfig = configurationRepo.save(configuration);

            //Return URI somehow necessary for succesful completion of Post task
            URI location = URI.create(baseURL + newConfig.getId());
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setLocation(location);

            return new ResponseEntity<>(newConfig, responseHeaders, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/configurations/{id}")
    public ResponseEntity<Configuration> updateConfiguration(@PathVariable("id") int id, @RequestBody Configuration configuration) {
        Optional<Configuration> configurationData = configurationRepo.findById(id);

        if (configurationData.isPresent()) {
            Configuration updatedConfig = configurationRepo.save(configuration);
//            Configuration _configuration = configurationData.get();
//            _configuration.setTitle(tutorial.getTitle());
//            _configuration.setDescription(tutorial.getDescription());
//            _configuration.setPublished(tutorial.isPublished());
            return new ResponseEntity<>(updatedConfig, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/configurations/{id}")
    public ResponseEntity<HttpStatus> deleteConfiguration(@PathVariable("id") int id) {
        try {
            configurationRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}