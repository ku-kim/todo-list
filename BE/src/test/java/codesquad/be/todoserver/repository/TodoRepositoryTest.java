package codesquad.be.todoserver.repository;

import static org.assertj.core.api.Assertions.assertThat;

import codesquad.be.todoserver.domain.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@DataJdbcTest
class TodoRepositoryTest {

	private TodoRepository todoRepository;

	@Autowired
	public TodoRepositoryTest(JdbcTemplate jdbcTemplate) {
		this.todoRepository = new TodoJdbcRepository(jdbcTemplate);
	}

	@Test
	void 특정_투두리스트_조회_성공() throws Exception {
		Todo todo = todoRepository.findById(1L).get();

		assertThat(todo.getId()).isEqualTo(1);
		assertThat(todo.getTitle()).isEqualTo("Github 공부하기");
		assertThat(todo.getContents()).isEqualTo("add, commit, push");
		assertThat(todo.getUser()).isEqualTo("sam");
		assertThat(todo.getStatus()).isEqualTo("todo");
	}

}
