package spring.core.domain.restaurant;

import java.math.BigDecimal;

public record MenuItem(
        String id,
        String name,
        String restaurantId,
        String description,
        BigDecimal price,
        String category,
        boolean available
) {
}
