package epam.spring.service;

import epam.spring.entity.Event;
import epam.spring.entity.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.List;

public class DiscountService implements DiscountServiceI {

    private List<DiscountStrategy> discountStrategyList;

    @Override
    public byte getDiscount(@Nullable User user,
                            @Nonnull Event event,
                            @Nonnull LocalDateTime airDateTime,
                            long numberOfTickets) {
        byte totalDiscount = 0;
        for (DiscountStrategy discountStrategy : discountStrategyList) {
            totalDiscount = (byte) Math.max(discountStrategy.getDiscount(user, event, airDateTime, numberOfTickets),
                    totalDiscount);
        }
        return totalDiscount;
    }
}
