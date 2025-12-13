package spring.core.controller.todo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.servlet.client.RestTestClient;
import spring.core.domain.todo.Todo;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 기존에는 테스트 방식이 굉장히 다양했다.
 * RestTestClient 는 그 모든 방식을 모두 지원한다.
 * 더 이상 어떤 라이브러리를 import 하고 해당 라이브러리의 테스트 코드 작성 방법과 동작 원리가 어떻게되고..
 * 이런 다양한 케이스에 대한 고려를 하지 않아도 된다!
 * 단순하고 직관적인 RestTestClient 하나의 일관된 방식을 제공한다.
 */
class SimpleTodoControllerTest {

  private RestTestClient client;

  @BeforeEach
  void setUp() {
    client = RestTestClient.bindToController(SimpleTodoController.class).build();
  }

  @Test
  void getAllTest() {
    List<Todo> todos = client.get()
      .uri("/api/todos/simple")
      .exchange()
      .expectStatus().isOk()
      .expectBody(new ParameterizedTypeReference<List<Todo>>() {
      })
      .returnResult()
      .getResponseBody();

    assertThat(todos).hasSize(1);
    assertThat(todos.getFirst().title()).isEqualTo("First Todo");

  }

  @Test
  void getById() {
    Todo todo = new Todo(1L, 1L, "First Todo", true);
    client.get()
      .uri("/api/todos/simple/1")
      .exchange()
      .expectStatus().isOk()
      .expectBody(Todo.class)
      .isEqualTo(todo);
  }
}
