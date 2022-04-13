package codesquad.be.todoserver.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Todo {

	private Long id;
	private final String title;
	private final String contents;
	private final String user;
	private final String status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime createdAt;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime updatedAt;

	public Todo(Long id, String title, String contents, String user, String status) {
		this.id = id;
		this.title = title;
		this.contents = contents;
		this.user = user;
		this.status = status;
	}

	public Todo(String title, String contents, String user, String status) {
		this.title = title;
		this.contents = contents;
		this.user = user;
		this.status = status;
	}

}
