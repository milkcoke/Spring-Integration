package spring.core.service.todo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.*;
import spring.core.domain.todo.Todo;

import java.util.List;

@HttpExchange(
  url = "https://jsonplaceholder.typicode.com",
  accept = "application/json"
)
public interface NewerTodoService {
  /**
   * Just define interface here, at runtime, <br>
   * Spring will generate the implementation class automatically. <br>
   * Using RestClient
   */
  @GetExchange("/todos")
  List<Todo> findAllTodos();

  @GetExchange("/todos/{id}")
  Todo findTodoById(@PathVariable Long id);

  @GetExchange("/todos?userId={userId}")
  List<Todo> getTodosByUserId(@PathVariable Long userId);

  @PostExchange("/todos")
  Todo create(Todo todo);

  @PutExchange("/todos/{id}")
  Todo updateTodo(@PathVariable Long id, @RequestBody Todo todo);

  @DeleteExchange("/todos/{id}")
  void deleteTodo(@PathVariable Long id);
}
