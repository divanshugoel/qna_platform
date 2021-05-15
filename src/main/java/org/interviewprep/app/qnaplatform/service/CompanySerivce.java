package org.interviewprep.app.qnaplatform.service;

import java.util.List;

import org.interviewprep.app.qnaplatform.model.Company;
import org.interviewprep.app.qnaplatform.requestdto.CompanyRequestDTO;

public interface CompanySerivce {

	public Company createCompany(CompanyRequestDTO companyRequestDTO) throws Exception;

	public Company updateCompany(CompanyRequestDTO companyRequestDTO);

	public Company getCompany(Integer id);

	public void deleteCompany(Integer id);

	public List<Company> getAllCompany();

}
