package se.bth.Rental.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.bth.Rental.models.Product;
import se.bth.Rental.repository.ProductRepository;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
//@RequestMapping("/apii")//we need this path to fetch by adding to path this word "/apii" as example another methods like GET,PUT ...
public class ProductController {
    private final Logger log = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductRepository productRepo;

    public ProductController(ProductRepository customerRepo) {
        this.productRepo = productRepo;
    }
    @GetMapping("/products")
    Collection<Product> products() {
        return productRepo.findAll();
    }

    @GetMapping("/product/{id}")
    ResponseEntity<?> getProduct(@PathVariable Long id) {
        Optional<Product> product = productRepo.findById(id);
        return product.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/product",consumes= MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) throws URISyntaxException {
        log.info("Request to create product: {}", product);
        Product result = productRepo.save(product);
        return ResponseEntity.created(new URI("/api/product/" + result.getId())).body(result);
    }

    @PutMapping("/product")
    ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product) {
        log.info("Request to update product: {}", product);
        Product result = productRepo.save(product);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        log.info("Request to delete product: {}", id);
        productRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
