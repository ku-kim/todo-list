package codesquad.be.todoserver.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import codesquad.be.todoserver.domain.Todo;
import codesquad.be.todoserver.repository.TodoRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class TodoServiceTest {

	private TodoRepository todoRepository = mock(TodoRepository.class);
	private TodoService todoService = new TodoService(todoRepository);

	@Test
	void 특정_투두리스트_조회_성공() throws Exception {
		given(todoRepository.findById(1L))
			.willReturn(
				Optional.of(new Todo(1L, "Github 공부하기", "add, commit, push", "sam", "todo")));

		Todo todo = todoService.getById(1L);

		assertThat(todo.getId()).isEqualTo(1);
		assertThat(todo.getTitle()).isEqualTo("Github 공부하기");
		assertThat(todo.getContents()).isEqualTo("add, commit, push");
		assertThat(todo.getUser()).isEqualTo("sam");
		assertThat(todo.getStatus()).isEqualTo("todo");
	}
}
