package epam.spring.entity.counter;

import epam.spring.entity.AbstractEntity;
import epam.spring.entity.User;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import javax.annotation.Nullable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"strategy", "count"}, callSuper = true)
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DiscountCounter extends AbstractEntity {
    String strategy;
    Long count;
    @Nullable
    User user;
}
