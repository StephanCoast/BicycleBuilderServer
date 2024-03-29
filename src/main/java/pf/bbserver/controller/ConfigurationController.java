package pf.bbserver.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pf.bbserver.model.Configuration;
import pf.bbserver.repository.ConfigurationRepo;

import java.net.URI;
import java.util.*;


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

    @PutMapping("/configurations/{id}/writeAccess")
    public ResponseEntity<String> switchWriteAccessConfiguration(@PathVariable("id") int id) {
        Optional<Configuration> configurationData = configurationRepo.findById(id);
        if (configurationData.isPresent()) {
            Configuration _configuration = configurationData.get();

            // Check if item is currently being edited
            String authUserName = SecurityContextHolder.getContext().getAuthentication().getName();
            String writeAccess = _configuration.getWriteAccess();
            if (writeAccess == null) {
                _configuration.setWriteAccess(authUserName); // Zugriff setzen
                Configuration updatedConfig = configurationRepo.save(_configuration);
                return new ResponseEntity<>("ACCESS GRANTED to " + authUserName, HttpStatus.OK);
            } else if (writeAccess.equals(authUserName)){
                _configuration.setWriteAccess(null); // Zugriff freigeben
                Configuration updatedConfig = configurationRepo.save(_configuration);
                return new ResponseEntity<>("ACCESS RETURNED by " + authUserName, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("ACCESS DENIED: Configuration is currently being edited by:" + _configuration.getWriteAccess(), HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<>("ACCESS GRANTED", HttpStatus.OK);
        }
    }

    @PutMapping("/configurations/{id}")
    public ResponseEntity<Configuration> updateConfiguration(@PathVariable("id") int id, @RequestBody Configuration configuration) {
        Optional<Configuration> configurationData = configurationRepo.findById(id);

        if (configurationData.isPresent()) {
            Configuration _configuration = configurationData.get();

            // Check if item is currently being edited
            String authUserName = SecurityContextHolder.getContext().getAuthentication().getName();
            String writeAccess = _configuration.getWriteAccess();
            if(writeAccess == null)
                    writeAccess = ""; // Für equals Vergleich einen leeren String setzen

            if (writeAccess.equals(authUserName)) {
                    //configuration.setWriteAccess(null); // Zugriff wieder freigeben
                    Configuration updatedConfig = configurationRepo.save(configuration);
                    return new ResponseEntity<>(updatedConfig, HttpStatus.OK);
            } else {
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
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