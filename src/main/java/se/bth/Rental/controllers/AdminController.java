package se.bth.Rental.controllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.bth.Rental.models.Admin;
import se.bth.Rental.repository.AdminRepository;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;
@RestController
//@RequestMapping("/apii")//we can do overWriting to endpoint path in application.properties file
public class AdminController {
    private final Logger log = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private AdminRepository adminRepo;
    public AdminController(AdminRepository adminRepo) {//constructor
        this.adminRepo = adminRepo;
    }

    @GetMapping("/admins")
    Collection<Admin> admins() {
        return adminRepo.findAll();
    }
    @GetMapping("/admin/{id}")
    ResponseEntity<?> getAdmin(@PathVariable Long id) {
        Optional<Admin> admin = adminRepo.findById(id);
        return admin.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping(value = "/admin", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Admin> createAdmin(@Valid @RequestBody Admin admin) throws URISyntaxException {
        log.info("Request to create admin: {}", admin);
        Admin result = adminRepo.save(admin);
        return ResponseEntity.created(new URI("/api/admin/" + result.getId())).body(result);
    }
    @PutMapping("/admin")           //@RequestMapping(value = "/Admin",method = PUT)
    ResponseEntity<Admin> updateAdmin(@Valid @RequestBody Admin admin) {
        log.info("Request to update admin: {}", admin);
        Admin result = adminRepo.save(admin);
        return ResponseEntity.ok().body(result);
    }
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long id) {
        log.info("Request to delete admin: {}", id);
        adminRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }

}