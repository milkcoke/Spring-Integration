package spring.core.domain.todo;

public record Todo(
  Long id,
  Long userId,
  String title,
  Boolean completed
) {
}
