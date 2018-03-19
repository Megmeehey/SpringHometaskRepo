package epam.spring.config;

import epam.spring.entity.Auditorium;
import epam.spring.repo.AuditoriumRepoI;
import epam.spring.repo.EventRepoI;
import epam.spring.repo.TicketRepoI;
import epam.spring.repo.UserRepoI;
import epam.spring.repo.map.AuditoriumMapRepo;
import epam.spring.repo.map.DiscountCounterMapRepo;
import epam.spring.repo.map.EventCounterMapRepo;
import epam.spring.repo.map.EventMapRepo;
import epam.spring.repo.map.TicketMapRepo;
import epam.spring.repo.map.UserMapRepo;
import epam.spring.service.AuditoriumService;
import epam.spring.service.AuditoriumServiceI;
import epam.spring.service.BookingService;
import epam.spring.service.EventService;
import epam.spring.service.EventServiceI;
import epam.spring.service.UserService;
import epam.spring.service.UserServiceI;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableAspectJAutoProxy
@Import({DiscountConfig.class, AuditoriumConfig.class})
@ComponentScan({"epam.spring"})
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AppConfig {
    @Autowired
    DiscountConfig discountConfig;
    @Autowired
    AuditoriumConfig auditoriumConfig;

    @Bean
    public BookingService bookingService() {
        return new BookingService(ticketRepository(), discountConfig.discountService());
    }

    @Bean
    public AuditoriumServiceI auditoriumService() {
        return new AuditoriumService(auditoriumRepo());
    }

    @Bean
    public AuditoriumRepoI auditoriumRepo() {
        Set<Auditorium> auditoriums = new HashSet<>();
        Collections.addAll(auditoriums, auditoriumConfig.bigAuditorium(), auditoriumConfig.mediumAuditorium(), auditoriumConfig.smallAuditorium());
        return new AuditoriumMapRepo(auditoriums);
    }

    @Bean Set<Auditorium> auditoriums() {
        Set<Auditorium> auditoriums = new HashSet<>();
        Collections.addAll(auditoriums, auditoriumConfig.bigAuditorium(), auditoriumConfig.mediumAuditorium(), auditoriumConfig.smallAuditorium());
        return auditoriums;
    }

    @Bean
    public EventServiceI eventService() {
        return new EventService(eventRepository());
    }

    @Bean
    public UserServiceI userService() {
        return new UserService(userRepository());
    }

    @Bean
    public DiscountCounterMapRepo discountCounterRepository() {
        return new DiscountCounterMapRepo();
    }

    @Bean
    public EventCounterMapRepo eventCounterRepository() {
        return new EventCounterMapRepo();
    }

    @Bean
    public EventRepoI eventRepository() {
        return new EventMapRepo();
    }

    @Bean
    public TicketRepoI ticketRepository() {
        return new TicketMapRepo();
    }

    @Bean
    public UserRepoI userRepository() {
        return new UserMapRepo();
    }
}
