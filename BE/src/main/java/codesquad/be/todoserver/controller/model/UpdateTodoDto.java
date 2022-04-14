package codesquad.be.todoserver.controller.model;

import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTodoDto {

	@Size(max = 250)
	private String title;

	@Size(max = 500)
	private String contents;

	@Size(max = 10)
	private String status;

}
