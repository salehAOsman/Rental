package se.bth.Rental.handling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.bth.Rental.models.*;
import se.bth.Rental.repository.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class Intializer implements CommandLineRunner {

    @Autowired
    private final CustomerRepository customerrepo;
    @Autowired
    private final AdminRepository adminrepo;
    @Autowired
    private final ProductRepository productrepo;
    @Autowired
    private final AvailableTimeRepository avaiTimeRepo;
    @Autowired
    private final KnowledgeRepository knowledgeRepo;
    @Autowired
    private final ReservationRepository reservationRepo;
    @Autowired
    private final SkillRepository skillRepo;
    @Autowired
    private final UserRepository userRepo;
    public Intializer(CustomerRepository repository, AdminRepository adminrepo, ProductRepository productrepo,
                      AvailableTimeRepository avaiTimeRepo, KnowledgeRepository knowledgeRepository,
                      ReservationRepository reservationRepo, SkillRepository skillRepo, UserRepository userRepo) {
        this.customerrepo = repository;
        this.adminrepo = adminrepo;
        this.productrepo = productrepo;
        this.avaiTimeRepo = avaiTimeRepo;
        this.knowledgeRepo =knowledgeRepository;
        this.reservationRepo = reservationRepo;
        this.skillRepo = skillRepo;
        this.userRepo = userRepo;
    }
    @Override
    public void run(String... strings) {
        addPersons();
        addProduct();
        addKnowledge();
        addSkill();
        //addReservation();
        addUsers();
    }
    private void addUsers() {
        userRepo.save( new User("user1","$2a$04$1.YhMIgNX/8TkCKGFUONWO!waedKhQ5KrnB30fl0Q01QKqmzLf.Zi" ,"USER"));
        userRepo.save( new User("admin","$2a$04$KNLUwOWHVQZVpXyMBNc7JOzbLiBjb9Tk9bP7KNcPI12ICuvzXQQKG" ,"ADMIN"));//be carefully username is unique then no double save for same username
    }

    /*private void addReservation() {
    } */
    private void addSkill() {

        List<AvailableTime> availableTimes = new ArrayList<>();
        AvailableTime e = AvailableTime.builder().day(DayOfWeek.MONDAY).start(LocalTime.of(5, 0))
                .end(LocalTime.of(10, 0)).build();
        availableTimes.add(e);
        avaiTimeRepo.save(e);
        e = AvailableTime.builder().day(DayOfWeek.TUESDAY).start(LocalTime.of(12, 0))
                .end(LocalTime.of(17, 30)).build();
        availableTimes.add(e);
        e = AvailableTime.builder().day(DayOfWeek.FRIDAY).start(LocalTime.of(12, 0))
                .end(LocalTime.of(17, 30)).build();
        availableTimes.add(e);
        avaiTimeRepo.saveAll(availableTimes);
        Customer customer = (Customer) customerrepo.findByFirstName("Anna");
        Skill s = Skill.builder().name("Camera").quantity(1).pricePerHour(50.0).status(ResourceStatus.AVAILABLE).category(Category.Electronics_Computers).description("Home camera")
                .proof("12333-sds").owner(customer).availableTime(availableTimes).build();
        skillRepo.save(s);
        availableTimes = new ArrayList<>();
        e = AvailableTime.builder().day(DayOfWeek.SATURDAY).start(LocalTime.of(0, 0))
                .end(LocalTime.of(23, 59)).build();
        availableTimes.add(e);
        avaiTimeRepo.save(e);
        e = AvailableTime.builder().day(DayOfWeek.SUNDAY).start(LocalTime.of(0, 0))
                .end(LocalTime.of(23, 59)).build();
        availableTimes.add(e);
        avaiTimeRepo.saveAll(availableTimes);
        customer = (Customer) customerrepo.findByFirstName("Olle");
        s = Skill.builder().name("Car").quantity(1).pricePerHour(500.0).status(ResourceStatus.AVAILABLE).category(Category.Electronics_Computers).description("Volvo")
                .owner(customer).proof("1235-25lj-vc12").availableTime(availableTimes).build();
        skillRepo.save(s);
        skillRepo.findAll().forEach(System.out::println);
    }

    private void addKnowledge() {
        List<AvailableTime> availableTimes = new ArrayList<>();
        AvailableTime e = AvailableTime.builder().day(DayOfWeek.MONDAY).start(LocalTime.of(8, 0))
                .end(LocalTime.of(15, 0)).build();
        availableTimes.add(e);
        avaiTimeRepo.save(e);
        e = AvailableTime.builder().day(DayOfWeek.TUESDAY).start(LocalTime.of(10, 0))
                .end(LocalTime.of(17, 30)).build();
        availableTimes.add(e);
        e = AvailableTime.builder().day(DayOfWeek.FRIDAY).start(LocalTime.of(12, 0))
                .end(LocalTime.of(20, 0)).build();
        availableTimes.add(e);
        avaiTimeRepo.saveAll(availableTimes);
        Customer customer = (Customer) customerrepo.findByFirstName("Fredrik");
        Knowledge n = Knowledge.builder().name("Katalok of Camera").quantity(1).pricePerHour(25.0).status(ResourceStatus.AVAILABLE).category(Category.Books).description("book description how camera works")
                .type(Type.KATALOG).owner(customer).availableTime(availableTimes).build();
        knowledgeRepo.save(n);
        availableTimes = new ArrayList<>();
        e = AvailableTime.builder().day(DayOfWeek.SATURDAY).start(LocalTime.of(0, 0))
                .end(LocalTime.of(23, 59)).build();
        availableTimes.add(e);
        avaiTimeRepo.save(e);
        e = AvailableTime.builder().day(DayOfWeek.SUNDAY).start(LocalTime.of(0, 0))
                .end(LocalTime.of(23, 59)).build();
        availableTimes.add(e);
        avaiTimeRepo.saveAll(availableTimes);
        customer = (Customer) customerrepo.findByFirstName("Erik");
        n = Knowledge.builder().name("Printer Drive").quantity(1).pricePerHour(20.5).status(ResourceStatus.AVAILABLE).category(Category.Books).description("Drive for install identifier for printer")
                .type(Type.CD).owner(customer).availableTime(availableTimes).build();
        knowledgeRepo.save(n);
        knowledgeRepo.findAll().forEach(System.out::println);

    }

    private void addProduct() {
        List<AvailableTime> availableTimes = new ArrayList<>();
        AvailableTime e = AvailableTime.builder().day(DayOfWeek.MONDAY).start(LocalTime.of(5, 0))
                .end(LocalTime.of(10, 0)).build();
        availableTimes.add(e);
        avaiTimeRepo.save(e);
        e = AvailableTime.builder().day(DayOfWeek.TUESDAY).start(LocalTime.of(12, 0))
                .end(LocalTime.of(17, 30)).build();
        availableTimes.add(e);
        e = AvailableTime.builder().day(DayOfWeek.FRIDAY).start(LocalTime.of(12, 0))
                .end(LocalTime.of(17, 30)).build();
        availableTimes.add(e);
        avaiTimeRepo.saveAll(availableTimes);
        Customer customer = (Customer) customerrepo.findByFirstName("Anna");
        Product p = Product.builder().name("Camera").quantity(1).pricePerHour(50.0).status(ResourceStatus.AVAILABLE).category(Category.Electronics_Computers).description("Home camera")
                .serialNumber("11-25k-oi12").owner(customer).availableTime(availableTimes).build();
        productrepo.save(p);
        availableTimes = new ArrayList<>();
        e = AvailableTime.builder().day(DayOfWeek.SATURDAY).start(LocalTime.of(0, 0))
                .end(LocalTime.of(23, 59)).build();
        availableTimes.add(e);
        avaiTimeRepo.save(e);
        e = AvailableTime.builder().day(DayOfWeek.SUNDAY).start(LocalTime.of(0, 0))
                .end(LocalTime.of(23, 59)).build();
        availableTimes.add(e);
        avaiTimeRepo.saveAll(availableTimes);
        customer = (Customer) customerrepo.findByFirstName("Olle");
        p = Product.builder().name("Car").quantity(1).pricePerHour(500.0).status(ResourceStatus.AVAILABLE).category(Category.Electronics_Computers).description("Volvo")
                .owner(customer).serialNumber("1235-25lj-vc12").availableTime(availableTimes).build();
        productrepo.save(p);
        productrepo.findAll().forEach(System.out::println);
    }

    private void addPersons() {
        Customer c = Customer.builder().firstName("Erik").lastName("Juhanson").email("erik@bth.se").city("Karlskrona").status(CustomerStatus.ACTIVE).payCard("111-528-9999").build();
        customerrepo.save(c);
        c = Customer.builder().firstName("Fredrik").lastName("Svensson").email("fridrek@bth.se").city("Karlshamen").status(CustomerStatus.ACTIVE).payCard("124-318-5555").build();
        customerrepo.save(c);
        c = Customer.builder().firstName("Olle").lastName("Hamen").email("olle@bth.se").city("Malmö").status(CustomerStatus.ACTIVE).payCard("214-333-9254").build();
        customerrepo.save(c);
        c = Customer.builder().firstName("Anna").lastName("Kronson").email("anna@bth.se").city("Väsjö").status(CustomerStatus.ACTIVE).payCard("521-318-3213").build();
        customerrepo.save(c);
        Admin a = Admin.builder().firstName("Erik").lastName("Juhanson").email("erik@bth.se").city("Lund").roll("owner").build();
        adminrepo.save(a);
        customerrepo.findAll().forEach(System.out::println);
        adminrepo.findAll().forEach(System.out::println);
    }
}