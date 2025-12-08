package spring.core.controller.driver;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.core.domain.driver.Driver;
import spring.core.domain.driver.DriverOrder;
import spring.core.service.driver.DriverAssignmentService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/drivers")
@RequiredArgsConstructor
@Slf4j
public class DriverController {

  private final DriverAssignmentService driverService;

  @PostMapping("/assign")
  public ResponseEntity<Map<String, Object>> assignDriver(
    @RequestParam String orderId
  ) {
    log.info("🚗 API request: Assign driver for order {}", orderId);

    try {
      // Create a sample order
      DriverOrder order = new DriverOrder(
        orderId,
        "customer-123",
        "rest-001",
        List.of("item-1", "item-2"),
        new BigDecimal("25.99"),
        "payment-123"
      );

      // This call uses RetryTemplate - watch the logs for detailed retry events!
      Driver driver = driverService.assignDriver(order);

      return ResponseEntity.ok(Map.of(
        "orderId", orderId,
        "driver", Map.of(
          "id", driver.id(),
          "name", driver.name(),
          "rating", driver.rating()
        ),
        "message", "Driver assigned successfully (possibly after retries)"
      ));

    } catch (Exception e) {
      log.error("❌ Failed to assign driver after all retries: {}", e.getMessage());
      return ResponseEntity.status(503).body(Map.of(
        "error", "No drivers available",
        "message", e.getMessage(),
        "orderId", orderId
      ));
    }
  }


}
