package pf.bbserver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pf.bbserver.model.Configuration;
import pf.bbserver.repository.ConfigurationRepo;

import java.util.List;
import java.util.Optional;


//@CrossOrigin(origins = "http://localhost:8081")
//@RequestMapping("/configurations")
@RestController
public class ConfigurationController {

    final ConfigurationRepo configurationRepo;

    public ConfigurationController(ConfigurationRepo configurationRepo) {
        this.configurationRepo = configurationRepo;
    }

    @GetMapping("/configurations")
    public ResponseEntity<List<Configuration>> getAll() {
        try {
            List<Configuration> configurations;
            configurations = configurationRepo.findAll();

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
    public ResponseEntity<Configuration> getArticleById(@PathVariable("id") long id) {
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
            Configuration createdConfiguration = configurationRepo.save(configuration);
            return new ResponseEntity<>(createdConfiguration, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/configurations/{id}")
    public ResponseEntity<Configuration> updateConfiguration(@PathVariable("id") long id, @RequestBody Configuration configuration) {
        Optional<Configuration> configurationData = configurationRepo.findById(id);

        if (configurationData.isPresent()) {
            Configuration existingConfiguration = configurationData.get();
            existingConfiguration.setStatus(configuration.getStatus());
            existingConfiguration.setDateLastChanged(configuration.getDateLastChanged());
            existingConfiguration.setWriteAccess(configuration.isWriteAccess());
            existingConfiguration.setUser(configuration.getUser());
            existingConfiguration.setOrderClass(configuration.getOrderClass());
            existingConfiguration.setArticles(configuration.getArticles());
            return new ResponseEntity<>(configurationRepo.save(configuration), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/configurations/{id}")
    public ResponseEntity<HttpStatus> deleteConfiguration(@PathVariable("id") long id) {
        try {
            configurationRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





}