package spring.core.controller.todo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.springframework.web.context.WebApplicationContext;
import spring.core.domain.todo.Todo;
import spring.core.service.todo.NewerTodoService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TodoBindToApplicationContextTest {

  @Autowired
  WebApplicationContext context;

  @Autowired
  NewerTodoService todoService;

  private RestTestClient client;

  @BeforeEach
  void setUp(WebApplicationContext context) {
    client = RestTestClient.bindToApplicationContext(context).build();
  }

  @Test
  void getAllTodos() {
    List<Todo> todos = client.get()
      .uri("/api/todos")
      .exchange()
      .expectStatus().isOk()
      .expectBody(new ParameterizedTypeReference<List<Todo>>(){})
      .returnResult()
      .getResponseBody();

    assertThat(todos).hasSize(1);
    assertThat(todos.getFirst().title()).isEqualTo("First Todo");
  }
}
