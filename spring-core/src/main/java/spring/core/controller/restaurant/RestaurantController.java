package spring.core.controller.restaurant;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.core.domain.restaurant.MenuItem;
import spring.core.domain.restaurant.Restaurant;
import spring.core.service.restaurant.RestaurantService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
@Slf4j
public class RestaurantController {
  private final RestaurantService restaurantService;

  @GetMapping()
  public List<Restaurant> getAllRestaurants() {
    return restaurantService.readAllRestaurants();
  }

  @GetMapping("/{restaurantId}/menu")
  public ResponseEntity<Map<String, Object>> getRestaurantMenu(@PathVariable String restaurantId) {
    try {
      List<MenuItem> menuItems = restaurantService.readMenusFromPartner(restaurantId);

      return ResponseEntity.ok(Map.of(
          "restaurantId", restaurantId,
          "menuItems", menuItems,
          "count", menuItems.size(),
          "message", "Menu fetched successfully (possibly after retries)"
        )
      );
    } catch (Exception e) {
      log.error("Failed to fetch menu after all retries: {}", e.getMessage());
      return ResponseEntity.status(503).body(Map.of(
        "error", "Service temporarily unavailable",
        "message", e.getMessage(),
        "restaurantId", restaurantId
      ));
    }
  }
}
