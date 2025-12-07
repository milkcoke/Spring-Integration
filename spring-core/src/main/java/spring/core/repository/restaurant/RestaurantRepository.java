package spring.core.repository.restaurant;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import spring.core.domain.restaurant.MenuItem;
import spring.core.domain.restaurant.Restaurant;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RestaurantRepository {

    private final JsonMapper jsonMapper;

    @Getter
    private final Map<String, Restaurant> restaurants = new ConcurrentHashMap<>();
    @Getter
    private final Map<String, MenuItem> menuItems = new ConcurrentHashMap<>();

    public Optional<Restaurant> findRestaurant(String id) {
        return Optional.ofNullable(restaurants.get(id));
    }
    public Optional<MenuItem> findMenuItem(String id) {
        return Optional.ofNullable(menuItems.get(id));
    }

    @PostConstruct
    private void loadData() throws IOException {
        var resource = new ClassPathResource("data/restaurants.json");
        List<Restaurant> restaurantList = jsonMapper.readValue(
                resource.getInputStream(),
                new TypeReference<>() {}
        );
        restaurantList.forEach(restaurant -> restaurants.put(restaurant.id(), restaurant));
        log.info("restaurants data loaded!");

        resource = new ClassPathResource("data/menu-items.json");
        List<MenuItem> menuItemList = jsonMapper.readValue(
                resource.getInputStream(),
                new TypeReference<>() {}
        );
        menuItemList.forEach(menuItem ->  menuItems.put(menuItem.id(), menuItem));
        log.info("menuItems data loaded!");
    }

}
