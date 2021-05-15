package org.interviewprep.app.qnaplatform.mapper;

import java.time.Instant;

import org.interviewprep.app.qnaplatform.model.Company;
import org.interviewprep.app.qnaplatform.requestdto.CompanyRequestDTO;

public class CompanyMapper {
	public static Company mapToVO(CompanyRequestDTO companyRequestDTO, Company company) {
		company.setCompanyName(companyRequestDTO.getCompanyName());
		company.setCreatedOn(Instant.now());
		return company;

	}

}
