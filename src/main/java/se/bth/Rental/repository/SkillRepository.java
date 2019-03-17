package se.bth.Rental.repository;


import org.springframework.transaction.annotation.Transactional;
import se.bth.Rental.models.Skill;

@Transactional
public interface SkillRepository extends ResourceBaseRepositories<Skill> {


}
