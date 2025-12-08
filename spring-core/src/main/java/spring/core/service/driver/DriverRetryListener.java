package spring.core.service.driver;

import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.core.retry.RetryListener;
import org.springframework.core.retry.RetryPolicy;
import org.springframework.core.retry.Retryable;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class DriverRetryListener implements RetryListener {
  private final AtomicInteger totalRetries = new AtomicInteger(0);
  private final AtomicInteger successfulRecoveries = new AtomicInteger(0);
  private final AtomicInteger finalFailures = new AtomicInteger(0);
  private final ThreadLocal<Integer> currentAttempt = ThreadLocal.withInitial(() -> 0);

  @Override
  public void beforeRetry(RetryPolicy retryPolicy, Retryable<?> retryable) {
    int attemptCount = currentAttempt.get() + 1;
    currentAttempt.set(attemptCount);
    totalRetries.incrementAndGet();
    log.info(
      "Retry Listener: Attempts # {} starting for operation {}",
      attemptCount,
      retryable.getClass().getSimpleName()
    );
  }

  @Override
  public void onRetrySuccess(RetryPolicy retryPolicy, Retryable<?> retryable, @Nullable Object result) {
    int  attemptCount = currentAttempt.get();
    if (attemptCount > 1) {
      successfulRecoveries.incrementAndGet();
      log.info(
        "RetryListener: Operation {} succeed after {} attempt(s)",
        retryable.getClass().getSimpleName(),
        attemptCount
        );
    } else {
      log.debug(
        "RetryListener: Operation {} succeed on first attempt",
        retryable.getClass().getSimpleName()
      );
    }
  }

  @Override
  public void onRetryFailure(RetryPolicy retryPolicy, Retryable<?> retryable, Throwable throwable) {
    int attemptCount = currentAttempt.get();
    finalFailures.incrementAndGet();
    log.error(
      "RetryListener: Operation {} failed after {} attempt(s) {}",
      retryable.getName(),
      attemptCount,
      throwable.getCause()
    );
  }
}
