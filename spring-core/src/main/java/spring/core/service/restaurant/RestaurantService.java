package spring.core.service.restaurant;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Service;
import spring.core.domain.restaurant.MenuItem;
import spring.core.domain.restaurant.Restaurant;
import spring.core.repository.restaurant.RestaurantRepository;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantService {
  private final RestaurantRepository restaurantRepository;
  private final Random random = new Random();

  @Retryable(
    includes = RuntimeException.class,
    maxRetries = 4,
    delay = 1000, // 1-second initial delay
    multiplier = 2.0 // Exponential backoff
  )
  public List<MenuItem> readMenusFromPartner(String restaurantId) {
    log.info("Fetching menu from restaurant partner API for: {}", restaurantId);

    if (random.nextDouble() < 0.4) {
      log.warn("Restaurant API failed! Will retry...");
      throw new RuntimeException("Partner restaurant API is temporarily unavailable!");
    }

    simulateDelay(Duration.ofMillis(200));

    Optional<Restaurant> restaurant = restaurantRepository.findRestaurant(restaurantId);
    if (restaurant.isEmpty()) throw new IllegalArgumentException("Restaurant not found id: " + restaurantId);

    return restaurant.get().menuItemIds().stream()
      .map(restaurantRepository::findMenuItem)
      .flatMap(Optional::stream)
      .filter(MenuItem::available)
      .toList();
  }

  public List<Restaurant> readAllRestaurants() {
    return restaurantRepository.getRestaurants()
      .values()
      .stream()
      .toList();
  }


  private void simulateDelay(Duration duration) {
    try {
      Thread.sleep(duration.toMillis());
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
