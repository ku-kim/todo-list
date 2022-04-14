package codesquad.be.todoserver.controller;

import codesquad.be.todoserver.controller.model.RegisterTodoDto;
import codesquad.be.todoserver.controller.model.UpdateTodoDto;
import codesquad.be.todoserver.domain.Todo;

public class TodoDtoMapper {

	private TodoDtoMapper() {
	}

	public static Todo toDomainFromRegisterTodoDto(RegisterTodoDto dto) {
		return new Todo(dto.getTitle(),
			dto.getContents(),
			dto.getUser(),
			dto.getStatus());
	}

	public static Todo toDomainFromUpdateTodoDto(UpdateTodoDto dto) {
		if (!dto.isvalid()) {
			throw new IllegalArgumentException();
		}
		return new Todo(dto.getTitle(),
			dto.getContents(),
			null,
			dto.getStatus());
	}

}
