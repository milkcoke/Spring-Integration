package spring.core.controller.todo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.servlet.client.RestTestClient;
import spring.core.domain.todo.Todo;
import spring.core.service.todo.NewerTodoService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// Test with Mocking Service
class TodoBindToControllerTest {
  private RestTestClient client;
  @BeforeEach
  void setUp() {
    NewerTodoService todoService = Mockito.mock(NewerTodoService.class);
    Mockito.when(todoService.findAllTodos()).thenReturn(
      List.of(new Todo(1L, 1L, "First Todo", true))
    );

    client = RestTestClient.bindToController(new TodoController(todoService)).build();
  }

  @Test
  void getTodos() {
    List<Todo> todos = client.get()
      .uri("/api/todos")
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
  }
}
