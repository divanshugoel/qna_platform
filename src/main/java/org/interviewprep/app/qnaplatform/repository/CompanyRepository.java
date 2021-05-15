package org.interviewprep.app.qnaplatform.repository;

import java.util.Optional;

import org.interviewprep.app.qnaplatform.model.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Integer> {

	public Optional<Company> findByCompanyName(String name);

	public Iterable<Company> findAllByOrderByCreatedOnDesc();
}
