package se.bth.Rental.controllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.bth.Rental.models.Customer;
import se.bth.Rental.repository.CustomerRepository;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("api")//we need this path to fetch by adding to path this word "/apii" as example another methods like GET,PUT ...
public class CustomerController {
    private final Logger log = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    private CustomerRepository customerRepo;

    public CustomerController(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }
    @RequestMapping(value = "/customers",method = GET)
    Collection<Customer> customers() {
        return customerRepo.findAll();
    }

    @GetMapping("/customer/{id}")
    ResponseEntity<?> getCustomer(@PathVariable Long id) {
        Optional<Customer> customer = customerRepo.findById(id);
        return customer.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/customer",consumes= MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) throws URISyntaxException {
        log.info("Request to create customer: {}", customer);
        Customer result = customerRepo.save(customer);
        return ResponseEntity.created(new URI("/api/customer/" + result.getId())).body(result);
    }

    @PutMapping("/customer")
    ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer) {
        log.info("Request to update customer: {}", customer);
        Customer result = customerRepo.save(customer);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        log.info("Request to delete customer: {}", id);
        customerRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }

}