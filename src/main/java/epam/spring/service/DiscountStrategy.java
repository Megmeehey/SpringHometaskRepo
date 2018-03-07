package epam.spring.service;

import epam.spring.entity.Event;
import epam.spring.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;

@Service
public interface DiscountStrategy {
    public byte getDiscount(@Nullable User user,
                     @Nonnull Event event,
                     @Nonnull LocalDateTime airDateTime,
                     long numberOfTickets);
}
