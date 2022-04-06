package codesquad.be.todoserver.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import codesquad.be.todoserver.domain.Todo;
import codesquad.be.todoserver.service.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(TodoController.class)
class TodoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TodoService todoService;

	@Test
	void 특정_투두리스트_조회_성공() throws Exception {
		given(todoService.getById(1L))
			.willReturn(new Todo(1L, "Github 공부하기", "add, commit, push", "sam", "todo"));

		ResultActions perform = mockMvc.perform(get("/api/todos/1"));

		perform
			.andExpect(status().isOk())
			.andExpect(jsonPath("id").value(1))
			.andExpect(jsonPath("title").value("Github 공부하기"))
			.andExpect(jsonPath("contents").value("add, commit, push"))
			.andExpect(jsonPath("user").value("sam"))
			.andExpect(jsonPath("status").value("todo"));
	}
}
