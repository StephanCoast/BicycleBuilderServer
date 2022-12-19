package pf.bbserver.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pf.bbserver.model.OrderClass;
import pf.bbserver.repository.OrderRepo;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@RestController
public class OrderController {

    final OrderRepo orderRepo;

    final static String baseURL= "http://localhost:8080/orders/";

    public OrderController(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @GetMapping
    public ResponseEntity<List<OrderClass>> getAllOrders() {
        try {

            List<OrderClass> orders = new ArrayList<>((Collection) orderRepo.findAll());

            if (orders.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(orders, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderClass> getOrderById(@PathVariable("id") int id) {
        Optional<OrderClass> orderData = orderRepo.findById(id);

        if (orderData.isPresent()) {
            return new ResponseEntity<>(orderData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderClass> createOrder(@RequestBody OrderClass order) {
        try {
            OrderClass newOrder = orderRepo.save(order);

            //Return URI somehow necessary for succesful completion of Post task
            URI location = URI.create(baseURL + newOrder.getId());
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setLocation(location);

            return new ResponseEntity<>(newOrder, responseHeaders, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<OrderClass> updateOrder(@PathVariable("id") int id, @RequestBody OrderClass order) {
        Optional<OrderClass> orderData = orderRepo.findById(id);

        if (orderData.isPresent()) {
            OrderClass updatedConfig = orderRepo.save(order);
//            OrderClass _order = orderData.get();
//            _order.setTitle(tutorial.getTitle());
//            _order.setDescription(tutorial.getDescription());
//            _order.setPublished(tutorial.isPublished());
            return new ResponseEntity<>(updatedConfig, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable("id") int id) {
        try {
            orderRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}