package spring.core.controller.todo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.core.domain.todo.Todo;

import java.util.List;

@RestController
@RequestMapping("/api/todos/simple")
public class SimpleTodoController {

  @GetMapping("")
  public List<Todo> getAll() {
    return List.of(new Todo(1L, 1L, "First Todo", true));
  }

  @GetMapping("/{id}")
  public Todo getById(@PathVariable Long id) {
    return new Todo(id, 1L, "First Todo", true);
  }
}
