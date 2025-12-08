package spring.core.service.driver;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.retry.RetryException;
import org.springframework.core.retry.RetryPolicy;
import org.springframework.core.retry.RetryTemplate;
import org.springframework.stereotype.Service;
import spring.core.domain.driver.Driver;
import spring.core.domain.driver.DriverOrder;
import spring.core.error.NoDriversAvailableException;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class DriverAssignmentService {
  private final List<Driver> drivers = new ArrayList<>();
  // Implicitly uses RetryPolicy.withDefaults();
  private final RetryTemplate retryTemplate;
  private final Random random = new Random();

  public DriverAssignmentService(DriverRetryListener driverRetryListener) {
    RetryPolicy retryPolicy = RetryPolicy.builder()
      .maxRetries(4)
      .delay(Duration.ofMillis(1000))
      .multiplier(2)
      .includes(NoDriversAvailableException.class)
      .build();

    this.retryTemplate = new RetryTemplate(retryPolicy);
    retryTemplate.setRetryListener(driverRetryListener);
  }

  public Driver assignDriver(DriverOrder order) throws RetryException {
    log.info("Attempting to assign driver with order {}", order.id());


    return retryTemplate.execute(() -> {
      if (random.nextDouble() < 0.5 || this.drivers.isEmpty() ) {
        throw new NoDriversAvailableException("No drivers in area , will retry...");
      }

      return this.drivers.get(random.nextInt(this.drivers.size()));
    });
  }

  @PostConstruct
  private void initDrivers() {
    this.drivers.addAll(List.of(
      new Driver("1", "Alex Johnson", 4.8),
      new Driver("2", "Maria Garcia", 4.9),
      new Driver("3", "James Wilson", 4.5),
      new Driver("4", "Sarah Chen", 4.7),
      new Driver("5", "Mike Roberts", 4.6)
    ));
  }
}
