package org.interviewprep.app.qnaplatform.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.interviewprep.app.qnaplatform.exception.AppException;
import org.interviewprep.app.qnaplatform.mapper.CompanyMapper;
import org.interviewprep.app.qnaplatform.model.Company;
import org.interviewprep.app.qnaplatform.repository.CompanyRepository;
import org.interviewprep.app.qnaplatform.requestdto.CompanyRequestDTO;
import org.interviewprep.app.qnaplatform.responseDTO.ErrorMessages;
import org.interviewprep.app.qnaplatform.service.CompanySerivce;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanySerivce {

	@Autowired
	CompanyRepository companyRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyServiceImpl.class);

	@Override
	public Company createCompany(CompanyRequestDTO companyRequestDTO) throws Exception {
		Company company = new Company();
		CompanyMapper.mapToVO(companyRequestDTO, company);
		if (existByName(company)) {
			LOGGER.error("cannot create a company, company already exists");
			throw new AppException(ErrorMessages.ALREADY_EXISTS.getErrorCode(),
					ErrorMessages.ALREADY_EXISTS.getErrorMessages());
		}
		companyRepository.save(company);
		LOGGER.info("company created with id = {}", company.getId());
		return company;
	}

	@Override
	public Company updateCompany(CompanyRequestDTO companyRequestDTO) {
		Integer id = companyRequestDTO.getId();
		if (!companyRepository.existsById(id)) {
			LOGGER.error("cannot update a company, company doesnot exists");
			throw new AppException(ErrorMessages.DOES_NOT_EXIST.getErrorCode(),
					ErrorMessages.DOES_NOT_EXIST.getErrorMessages());
		}
		Company company = companyRepository.findById(id).get();
		CompanyMapper.mapToVO(companyRequestDTO, company);
		companyRepository.save(company);
		LOGGER.info("company updated with id = {}", company.getId());
		return company;
	}

	@Override
	public Company getCompany(Integer id) {
		Company company = new Company();
		Optional<Company> companyEntity = companyRepository.findById(id);
		if (!companyEntity.isPresent()) {
			LOGGER.error("cannot get a company, company doesnot exists");
			throw new AppException(ErrorMessages.DOES_NOT_EXIST.getErrorCode(),
					ErrorMessages.DOES_NOT_EXIST.getErrorMessages());
		}
		company = companyEntity.get();
		return company;
	}

	@Override
	public void deleteCompany(Integer id) {
		if (!companyRepository.existsById(id)) {
			LOGGER.error("cannot delete a company, company doesnot exists");
			throw new AppException(ErrorMessages.DOES_NOT_EXIST.getErrorCode(),
					ErrorMessages.DOES_NOT_EXIST.getErrorMessages());
		}
		companyRepository.deleteById(id);
		LOGGER.info("company deleted with id = {}", id);
	}

	@Override
	public List<Company> getAllCompany() {
		List<Company> companyList = new ArrayList<>();
		Iterable<Company> companyEntityList = companyRepository.findAllByOrderByCreatedOnDesc();
		for (Company company : companyEntityList) {
			companyList.add(company);
		}
		return companyList;
	}

	private boolean existByName(Company company) {
		Optional<Company> companyName = companyRepository.findByCompanyName(company.getCompanyName());
		if (companyName.isPresent()) {
			return true;
		}
		return false;
	}

}
