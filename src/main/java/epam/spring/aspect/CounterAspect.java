package epam.spring.aspect;

import epam.spring.repo.map.EventCounterMapRepo;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CounterAspect {
    private EventCounterMapRepo eventCounterRepo;

    @After("execution(* epam.spring.service.EventService.getByName(..))")
    private void countCallingsByName(JoinPoint jp) {
        eventCounterRepo.addToCounterByName(1);
    }

    @After("execution(* epam.spring.service.BookingService.getTicketsPrice(..))")
    private void countGettingPrice(JoinPoint jp) {
        eventCounterRepo.addToCounterByPrice(1);
    }

    @After("execution(* epam.spring.service.BookingService.bookTickets(..))")
    private void countBooking(JoinPoint jp) {
        eventCounterRepo.addToCounterByBooking(1);
    }

}
