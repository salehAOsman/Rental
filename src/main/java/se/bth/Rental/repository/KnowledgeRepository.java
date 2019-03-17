package se.bth.Rental.repository;


import org.springframework.transaction.annotation.Transactional;
import se.bth.Rental.models.Knowledge;

@Transactional
public interface KnowledgeRepository extends ResourceBaseRepositories<Knowledge> {

}
