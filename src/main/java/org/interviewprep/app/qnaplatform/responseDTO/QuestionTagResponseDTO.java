package org.interviewprep.app.qnaplatform.responseDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuestionTagResponseDTO {

	private Integer id;
	private String tagName;
	private String createdOn;
}
