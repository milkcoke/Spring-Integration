package milkcoke.core.domain.member;

public record Member(
    Long id,
    String name,
    Grade grade
) {
}
