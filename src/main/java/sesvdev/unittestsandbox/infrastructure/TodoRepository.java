package sesvdev.unittestsandbox.infrastructure;

import org.springframework.stereotype.Repository;
import sesvdev.unittestsandbox.domain.Todo;
import sesvdev.unittestsandbox.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Repository
public class TodoRepository {

	private final List<Todo> todos;

	public TodoRepository() {
        todos = List.of(new Todo(1l, "Study", false), new Todo(2l,"Laundry", true));
    }

	public List<Todo> findAll() {
		return todos;
	}

	public Optional<Todo> findById(long id) {
		return Optional.of(todos.stream().filter(todo -> todo.id() == id).findFirst().orElseThrow(() -> new ResourceNotFoundException(id)));
	}
}
