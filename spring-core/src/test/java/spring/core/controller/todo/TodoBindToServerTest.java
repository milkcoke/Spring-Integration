package spring.core.controller.todo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.client.RestTestClient;
import spring.core.domain.todo.Todo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodoBindToServerTest {

  @LocalServerPort
  private int port;

  private RestTestClient client;

  @BeforeEach
  void setUp() {
    client = RestTestClient.bindToServer()
      .baseUrl("http://localhost:" + port)
      .build();
  }

  @Test
  void getTodoById() {

    client.get()
      .uri("/api/todos/1")
      .exchange()
      .expectStatus().isOk()
      .expectBody(Todo.class)
      .isEqualTo(new Todo(1L, 1L, "delectus aut autem", false));

  }
}
