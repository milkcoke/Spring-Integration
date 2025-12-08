package spring.core.domain.driver;

import java.math.BigDecimal;
import java.util.List;

public record DriverOrder(
  String id,
  String customerId,
  String restaurantId,
  List<String> items,
  BigDecimal totalAmount,
  String paymentId,
  String paymentConfirmation,
  OrderStatus status
) {
  // Convenience constructor for creating new orders (before payment confirmation)
  public DriverOrder(String id, String customerId, String restaurantId,
               List<String> items, BigDecimal totalAmount, String paymentId) {
    this(id, customerId, restaurantId, items, totalAmount, paymentId, null, OrderStatus.PENDING);
  }

  // Convenience method to create a copy with updated payment confirmation
  public DriverOrder withPaymentConfirmation(String paymentConfirmation) {
    return new DriverOrder(id, customerId, restaurantId, items, totalAmount, paymentId, paymentConfirmation, status);
  }

  // Convenience method to create a copy with updated status
  public DriverOrder withStatus(OrderStatus status) {
    return new DriverOrder(id, customerId, restaurantId, items, totalAmount, paymentId, paymentConfirmation, status);
  }

  public enum OrderStatus {
    PENDING,
    CONFIRMED,
    PREPARING,
    OUT_FOR_DELIVERY,
    DELIVERED,
    CANCELLED
  }
}
