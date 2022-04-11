package codesquad.be.todoserver.repository;

import codesquad.be.todoserver.domain.Todo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJdbcTest
@Sql({"/db/schema.sql", "/db/data.sql"})
@DisplayName("API /api/todos/* 리포지토리 계층 단위 테스트")
class TodoRepositoryTest {

	private TodoRepository todoRepository;

	@Autowired
	public TodoRepositoryTest(JdbcTemplate jdbcTemplate) {
		this.todoRepository = new TodoJdbcRepository(jdbcTemplate);
	}

	@Test
	void 특정_투두리스트_조회_성공() {
		Todo todo = todoRepository.findById(1L).get();

		assertAll(
				() -> assertThat(todo.getId()).isEqualTo(1),
				() -> assertThat(todo.getTitle()).isEqualTo("Github 공부하기"),
				() -> assertThat(todo.getContents()).isEqualTo("add, commit, push"),
				() -> assertThat(todo.getUser()).isEqualTo("sam"),
				() -> assertThat(todo.getStatus()).isEqualTo("todo")
		);
	}

	@Test
	void 특정_투두리스트_조회_실패() {
		Optional<Todo> todo = todoRepository.findById(4444L);

		assertThat(todo).isEmpty();
	}

	@Test
	void 전체_투두리스트_조회_성공() {
		List<Todo> todos = todoRepository.findAll().get();

		assertAll(
				() -> assertThat(todos.get(0).getId()).isEqualTo(1),
				() -> assertThat(todos.get(0).getTitle()).isEqualTo("Github 공부하기"),
				() -> assertThat(todos.get(0).getContents()).isEqualTo("add, commit, push"),
				() -> assertThat(todos.get(0).getUser()).isEqualTo("sam"),
				() -> assertThat(todos.get(0).getStatus()).isEqualTo("todo"),

				() -> assertThat(todos.get(1).getId()).isEqualTo(2),
				() -> assertThat(todos.get(1).getTitle()).isEqualTo("블로그에 포스팅할 것"),
				() -> assertThat(todos.get(1).getContents()).isEqualTo("*Github 공부내용 \\r\\n" + " *모던 자바스크립트 1장 공부내용"),
				() -> assertThat(todos.get(1).getUser()).isEqualTo("sam"),
				() -> assertThat(todos.get(1).getStatus()).isEqualTo("todo")
		);
	}
}