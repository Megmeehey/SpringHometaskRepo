package epam.spring.config;

import epam.spring.service.DiscountService;
import epam.spring.service.DiscountStrategy;
import epam.spring.service.discounts.DiscountStrategyBirthday;
import epam.spring.service.discounts.DiscountStrategyTenthTicket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class DiscountConfig {

    @Bean
    public DiscountService discountService() {
        List<DiscountStrategy> discountStrategies = new ArrayList<>();
        Collections.addAll(discountStrategies, discountStrategyBirthday(), discountStrategyTenthTicket());
        return new DiscountService(discountStrategies);
    }

    @Bean
    public DiscountStrategy discountStrategyBirthday() {
        return new DiscountStrategyBirthday();
    }

    @Bean
    public DiscountStrategy discountStrategyTenthTicket() {
        return new DiscountStrategyTenthTicket();
    }


}
