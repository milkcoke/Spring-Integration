package spring.core.controller.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.core.domain.todo.Todo;
import spring.core.service.todo.NewerTodoService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/todos")
public class TodoController {
  private final NewerTodoService todoService;

  @GetMapping("")
  public ResponseEntity<List<Todo>> getTodos() {
    return ResponseEntity.ok(todoService.findAllTodos());
  }
}
