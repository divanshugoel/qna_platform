package org.interviewprep.app.qnaplatform.requestdto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompanyRequestDTO {

	@NotNull(message = "company name cannot be null ")
	@NotEmpty(message = "company name cannot be empty ")
	private String companyName;
	
	private Integer id;

}
