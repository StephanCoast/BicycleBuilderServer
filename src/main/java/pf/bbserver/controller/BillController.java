package pf.bbserver.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pf.bbserver.model.Bill;
import pf.bbserver.repository.BillRepo;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@RestController
public class BillController {

    final BillRepo billRepo;

    final static String baseURL= "http://localhost:8080/bills/";

    public BillController(BillRepo billRepo) {
        this.billRepo = billRepo;
    }

    @GetMapping("/bills")
    public ResponseEntity<List<Bill>> getAllBills() {
        try {

            List<Bill> bills = new ArrayList<>((Collection) billRepo.findAll());

            if (bills.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(bills, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/bills/{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable("id") int id) {
        Optional<Bill> billData = billRepo.findById(id);

        if (billData.isPresent()) {
            return new ResponseEntity<>(billData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/bills")
    public ResponseEntity<Bill> createBill(@RequestBody Bill bill) {
        try {
            Bill newBill = billRepo.save(bill);

            //Return URI somehow necessary for succesful completion of Post task
            URI location = URI.create(baseURL + newBill.getId());
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setLocation(location);

            return new ResponseEntity<>(newBill, responseHeaders, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/bills/{id}")
    public ResponseEntity<Bill> updateBill(@PathVariable("id") int id, @RequestBody Bill bill) {
        Optional<Bill> billData = billRepo.findById(id);

        if (billData.isPresent()) {
            Bill updatedBill = billRepo.save(bill);
            return new ResponseEntity<>(updatedBill, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/bills/{id}")
    public ResponseEntity<HttpStatus> deleteBill(@PathVariable("id") int id) {
        try {
            billRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}