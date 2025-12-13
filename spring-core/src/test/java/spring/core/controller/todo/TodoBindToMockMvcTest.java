package spring.core.controller.todo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.RestTestClient;
import spring.core.domain.todo.Todo;
import spring.core.service.todo.NewerTodoService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * MockMvc Test creating a spring context <br>
 * SpringContext 에 Controller 관련 Bean 만 등록하며 테스트
*/
@WebMvcTest(TodoController.class)
public class TodoBindToMockMvcTest {

  @Autowired
  MockMvc mockMvc;

  @MockitoBean
  NewerTodoService todoService;

  RestTestClient client;

  @BeforeEach
  void setUp() {
    client = RestTestClient.bindTo(mockMvc).build();
  }

  @Test
  void getAll() {
    when(todoService.findAllTodos()).thenReturn(List.of(
      new Todo(1L, 1L, "First Todo", true)
    ));
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
}
