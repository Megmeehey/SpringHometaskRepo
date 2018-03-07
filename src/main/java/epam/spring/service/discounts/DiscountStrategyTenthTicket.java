package epam.spring.service.discounts;

import epam.spring.entity.Event;
import epam.spring.entity.User;
import epam.spring.service.DiscountStrategy;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;

@Service
public class DiscountStrategyTenthTicket implements DiscountStrategy {
    @Override
    public byte getDiscount(@Nullable User user,
                            @Nonnull Event event,
                            @Nonnull LocalDateTime airDateTime,
                            long numberOfTickets) {
        byte discount;
        if (user != null) {
            discount = (byte) (((user.getTickets().size() + numberOfTickets) % 10) > 0 ? 5 : 0);
        } else {
            discount = (byte) ((numberOfTickets % 10) > 0 ? 5 : 0);
        }
        return discount;
    }
}
