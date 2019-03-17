package se.bth.Rental.repository;


import org.springframework.transaction.annotation.Transactional;
import se.bth.Rental.models.Product;

@Transactional
public interface ProductRepository extends ResourceBaseRepositories<Product> {

}
