package se.bth.Rental.controllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.bth.Rental.models.Skill;
import se.bth.Rental.repository.SkillRepository;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
//@RequestMapping("/api")//we need this path to fetch by adding to path this word "/apii" as example another methods like GET,PUT ...
public class SkillController {

    private final Logger log = LoggerFactory.getLogger(SkillController.class);
    @Autowired
    private SkillRepository skillRepo;

    public SkillController(SkillRepository skillRepo) {
        this.skillRepo = skillRepo;
    }

    @GetMapping("/skills")
    Collection<Skill> skills() {
        return skillRepo.findAll();
    }

    @GetMapping("/skill/{id}")
    ResponseEntity<?> getSkill(@PathVariable Long id) {
        Optional<Skill> skill = skillRepo.findById(id);
        return skill.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/skill",consumes= MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Skill> createSkill(@Valid @RequestBody Skill skill) throws URISyntaxException {
        log.info("Request to create skill: {}", skill);
        Skill result = skillRepo.save(skill);
        return ResponseEntity.created(new URI("/api/skill/" + result.getId())).body(result);
    }

    @PutMapping("/skill")
    ResponseEntity<Skill> updateSkill(@Valid @RequestBody Skill skill) {
        log.info("Request to update skill: {}", skill);
        Skill result = skillRepo.save(skill);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/skill/{id}")
    public ResponseEntity<?> deleteSkill(@PathVariable Long id) {
        log.info("Request to delete skill: {}", id);
        skillRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }
}