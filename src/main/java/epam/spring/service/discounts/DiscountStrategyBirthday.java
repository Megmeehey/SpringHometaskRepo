package epam.spring.service.discounts;

import epam.spring.entity.Event;
import epam.spring.entity.User;
import epam.spring.service.DiscountStrategy;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;

@Service
public class DiscountStrategyBirthday implements DiscountStrategy {
    @Override
    public byte getDiscount(@Nullable User user,
                            @Nonnull Event event,
                            @Nonnull LocalDateTime airDateTime,
                            long numberOfTickets) {
        boolean birthdayDiscount = false;
        if (user != null) {
            birthdayDiscount = (user.getDateOfBirth().getMonthValue() == airDateTime.getMonthValue())
                    && (Math.abs(user.getDateOfBirth().getDayOfMonth() - airDateTime.getDayOfMonth()) <= 5);
        }
        return (byte) (birthdayDiscount ? 10 : 0);
    }
}
