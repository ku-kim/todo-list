package codesquad.be.todoserver.service;

import codesquad.be.todoserver.domain.Todo;
import codesquad.be.todoserver.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

	@Autowired
	private TodoRepository todoRepository;

	public TodoService(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	public Todo getById(Long id) {
		return todoRepository.findById(id)
			.orElseThrow(RuntimeException::new);
	}
}
