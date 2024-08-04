package sesvdev.unittestsandbox;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import sesvdev.unittestsandbox.application.TodoController;
import sesvdev.unittestsandbox.domain.Todo;
import sesvdev.unittestsandbox.infrastructure.TodoRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@WebMvcTest({TodoController.class})
class TodoControllerTest {

	@Autowired
	MockMvc mockMvc;
	@MockBean
	TodoRepository todoRepository;
	@Autowired
	ObjectMapper objectMapper;

	@Test
	void getAllTodos() throws Exception {
		var todos = List.of(new Todo(1l, "Study", false), new Todo(2l, "Laundry", true));
		when(todoRepository.findAll()).thenReturn(todos);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/todos")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpectAll(
						response -> MockMvcResultMatchers.status().isOk(),
						response -> MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(todos)),
						response -> MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(todos)),
						response -> MockMvcResultMatchers.jsonPath("$.[0].id").value(1l),
						response -> MockMvcResultMatchers.jsonPath("$.[0].name").value("Study"),
				        response -> MockMvcResultMatchers.jsonPath("$.[0].completed").value(false),
				        response -> MockMvcResultMatchers.jsonPath("$.[1].id").value(2l),
				        response -> MockMvcResultMatchers.jsonPath("$.[1].name").value("Laundry"),
				        response -> MockMvcResultMatchers.jsonPath("$.[1].completed").value(true));

}
@Test
    void getTodoById() throws Exception {
        var todo = new Todo(1l, "Study", false);
        when(todoRepository.findById(1L)).thenReturn(Optional.of(todo));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/todos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        response -> MockMvcResultMatchers.status().isOk(),
                        response -> MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(todo)),
                        response -> MockMvcResultMatchers.jsonPath("$.id").value(1l),
                        response -> MockMvcResultMatchers.jsonPath("$.name").value("Study"),
                        response -> MockMvcResultMatchers.jsonPath("$.completed").value(false));
    }
}