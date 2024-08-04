package sesvdev.unittestsandbox.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sesvdev.unittestsandbox.domain.Todo;
import sesvdev.unittestsandbox.infrastructure.TodoRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoController {

	private final TodoRepository todoRepository;

	public TodoController(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	@GetMapping("/todos")
    public List<Todo> getAllTodos() {
		return todoRepository.findAll();
    }
}
