package pf.bbserver.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pf.bbserver.model.Customer;
import pf.bbserver.repository.CustomerRepo;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@RestController
public class CustomerController {

    final CustomerRepo customerRepo;

    final static String baseURL= "http://localhost:8080/customers/";

    public CustomerController(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        try {

            List<Customer> customers = new ArrayList<>((Collection) customerRepo.findAll());

            if (customers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(customers, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") int id) {
        Optional<Customer> customerData = customerRepo.findById(id);

        if (customerData.isPresent()) {
            return new ResponseEntity<>(customerData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        try {
            Customer newCustomer = customerRepo.save(customer);

            //Return URI somehow necessary for succesful completion of Post task
            URI location = URI.create(baseURL + newCustomer.getId());
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setLocation(location);

            return new ResponseEntity<>(newCustomer, responseHeaders, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") int id, @RequestBody Customer customer) {
        Optional<Customer> customerData = customerRepo.findById(id);

        if (customerData.isPresent()) {
            Customer updatedConfig = customerRepo.save(customer);
            return new ResponseEntity<>(updatedConfig, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("id") int id) {
        try {
            customerRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}