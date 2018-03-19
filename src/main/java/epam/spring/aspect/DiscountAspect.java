package epam.spring.aspect;

import epam.spring.entity.User;
import epam.spring.repo.map.DiscountCounterMapRepo;
import epam.spring.service.discounts.DiscountStrategyBirthday;
import epam.spring.service.discounts.DiscountStrategyTenthTicket;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DiscountAspect {
    private DiscountCounterMapRepo discountCounterRepo;

    @After("execution(*  epam.spring.service.discounts.DiscountStrategyBirthday.getDiscount(..))")
    private void countBirthdayDiscountsCalling(JoinPoint jp) throws Exception {
        User user = (User) jp.getArgs()[0];
        if (user != null) {
            DiscountStrategyBirthday discountStrategyBirthday =
                    (DiscountStrategyBirthday) ((Advised) jp.getThis()).getTargetSource().getTarget();
            discountCounterRepo.save(user, discountStrategyBirthday);
        }
    }

    @After("execution(*  epam.spring.service.discounts.DiscountStrategyTenthTicket.getDiscount(..))")
    private void countTenthTicketDiscountsCalling(JoinPoint jp) throws Exception {
        User user = (User) jp.getArgs()[0];
        if (user != null) {
            DiscountStrategyTenthTicket discountStrategyTenthTicket =
                    (DiscountStrategyTenthTicket) ((Advised) jp.getThis()).getTargetSource().getTarget();
            discountCounterRepo.save(user, discountStrategyTenthTicket);
        }
    }
}
