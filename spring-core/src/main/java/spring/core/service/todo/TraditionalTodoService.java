package spring.core.service.todo;

import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import spring.core.domain.todo.Todo;

public class TraditionalTodoService {
  private final RestClient restClient;

  // Require `spring-boot-restclient` module
  public TraditionalTodoService(RestClient.Builder builder) {
    this.restClient = builder
      .baseUrl("https://jsonplaceholder.typicode.com")
      .build();
  }

  // There are a lot of boilerplate codes
  public Todo create(Todo todo) {
    return restClient.post()
      .uri("/todos")
      .contentType(MediaType.APPLICATION_JSON)
      .body(todo)
      .retrieve()
      .body(Todo.class);
  }

  public Todo readById(Integer id) {
    return restClient.get()
      .uri("/todos/{id}", id)
      .retrieve()
      .body(Todo.class);
  }

  public Todo update(Integer id, Todo todo) {
    return restClient.put()
      .uri("/todos/{id}", id)
      .contentType(MediaType.APPLICATION_JSON)
      .body(todo)
      .retrieve()
      .body(Todo.class);
  }

  public void delete(Integer id) {
    restClient.delete()
      .uri("/todos/{id}", id)
      .retrieve()
      .toBodilessEntity();
  }

}
