package epam.spring.service;

import epam.spring.entity.Event;
import epam.spring.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
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
