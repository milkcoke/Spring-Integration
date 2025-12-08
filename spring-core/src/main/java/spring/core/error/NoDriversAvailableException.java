package spring.core.error;

public class NoDriversAvailableException extends RuntimeException {
  public NoDriversAvailableException(String message) {
    super(message);
  }
}
