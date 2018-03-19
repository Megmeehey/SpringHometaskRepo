package epam.spring.repo.map;

import epam.spring.entity.User;
import epam.spring.entity.counter.DiscountCounter;
import epam.spring.service.DiscountStrategy;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class DiscountCounterMapRepo {
    private ConcurrentHashMap<String, DiscountCounter> totalDiscounts = new ConcurrentHashMap<>();
    private List<DiscountCounter> userDiscounts = new ArrayList<>();

    public void save(User user, DiscountStrategy strategy) {
        final List<DiscountCounter> discountCounters = getDiscountCountersByUser(user).stream()
                .filter(discountCounter -> strategy.equals(discountCounter.getStrategy()))
                .collect(Collectors.toList());
        if (discountCounters.size() == 0) {
            this.save(new DiscountCounter().setUser(user).setStrategy(strategy.getClass().getSimpleName()).setCount(1L));
        } else {
            final DiscountCounter discountCounter = discountCounters.get(0);
            userDiscounts.remove(discountCounter);
            userDiscounts.add(discountCounter.setCount(discountCounter.getCount() + 1));
            final DiscountCounter totalCounter = totalDiscounts.get(discountCounter.getStrategy());
            if (totalCounter != null) {
                totalDiscounts.put(totalCounter.getStrategy(), totalCounter.setCount(totalCounter.getCount() + 1));
            } else {
                totalDiscounts.put(discountCounter.getStrategy(),
                        new DiscountCounter()
                                .setUser(null)
                                .setStrategy(discountCounter.getStrategy())
                                .setCount(1L));
            }
        }
    }

    private void save(DiscountCounter discountCounter) {
        userDiscounts.add(discountCounter);
        final DiscountCounter totalCounter = totalDiscounts.get(discountCounter.getStrategy());
        totalDiscounts.put(totalCounter.getStrategy(), totalCounter.setCount(totalCounter.getCount() + 1));
    }

    public List<DiscountCounter> getDiscountCountersByUser(User user) {
        return userDiscounts.stream()
                .filter(discountCounter -> user.equals(discountCounter.getUser()))
                .collect(Collectors.toList());
    }

    public List<DiscountCounter> getDiscountCounterInTotal(DiscountStrategy discountStrategy) {
        return new ArrayList<>(totalDiscounts.values());
    }
}
