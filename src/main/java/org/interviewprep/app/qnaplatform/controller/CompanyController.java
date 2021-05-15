package org.interviewprep.app.qnaplatform.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.interviewprep.app.qnaplatform.model.Company;
import org.interviewprep.app.qnaplatform.requestdto.CompanyRequestDTO;
import org.interviewprep.app.qnaplatform.responseDTO.CompanyResponseDTO;
import org.interviewprep.app.qnaplatform.service.impl.CompanyServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
@RequestMapping("/api/qna/company")
public class CompanyController {

	@Autowired
	CompanyServiceImpl companyService;

	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);

	@PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CompanyResponseDTO> createCompany(@Valid @RequestBody CompanyRequestDTO companyRequestDTO)
			throws Exception {
		LOGGER.info("Inside createCompany");
		Company createdCompany = companyService.createCompany(companyRequestDTO);
		CompanyResponseDTO companyResponseDTO = prepareResponse(createdCompany);

		final URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/api/qna/company/{id}")
				.build().expand(createdCompany.getId()).toUri();
		final HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);
		LOGGER.info("Exiting createCompany");
		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(companyResponseDTO);

	}

	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CompanyResponseDTO> updateCompany(@Valid @RequestBody CompanyRequestDTO companyRequestDTO) {
		LOGGER.info("Inside updateCompany");
		Company updateCompany = companyService.updateCompany(companyRequestDTO);
		CompanyResponseDTO companyResponseDTO = prepareResponse(updateCompany);
		LOGGER.info("Exiting updateCompany");
		return ResponseEntity.status(HttpStatus.OK).body(companyResponseDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CompanyResponseDTO> getCompany(@PathVariable("id") Integer companyId) {
		LOGGER.info("Inside getCompany");
		Company company = companyService.getCompany(companyId);
		CompanyResponseDTO companyResponseDTO = prepareResponse(company);
		LOGGER.info("Exiting getCompany");
		return ResponseEntity.status(HttpStatus.OK).body(companyResponseDTO);
	}

	@GetMapping("/all")
	public ResponseEntity<List<CompanyResponseDTO>> getAllCompany() {
		LOGGER.info("Inside getAllCompany");
		List<CompanyResponseDTO> companyResponseDTOList = new ArrayList<>();
		List<Company> allCompanyList = companyService.getAllCompany();
		for (Company company : allCompanyList) {
			CompanyResponseDTO companyResponseDTO = prepareResponse(company);
			companyResponseDTOList.add(companyResponseDTO);
		}
		LOGGER.info("Exiting getAllCompany");
		return ResponseEntity.status(HttpStatus.OK).body(companyResponseDTOList);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCompany(@PathVariable("id") Integer companyId) {
		LOGGER.info("Inside deleteCompany");
		companyService.deleteCompany(companyId);
		LOGGER.info("Exiting deleteCompany");
		return ResponseEntity.status(HttpStatus.OK).body("company deleted with id = " + companyId);
	}

	private CompanyResponseDTO prepareResponse(Company createdCompany) {
		CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO();
		companyResponseDTO.setCompanyName(createdCompany.getCompanyName());
		companyResponseDTO.setId(createdCompany.getId());
		return companyResponseDTO;
	}

}
