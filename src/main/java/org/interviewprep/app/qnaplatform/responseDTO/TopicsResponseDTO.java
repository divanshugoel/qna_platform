package org.interviewprep.app.qnaplatform.responseDTO;

import java.time.Instant;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TopicsResponseDTO {
	private Integer id;
	private String topicName;
	private boolean isSubTopic;
	private String topicReference;
	private Instant createdOn;
}
