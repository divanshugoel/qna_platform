package org.interviewprep.app.qnaplatform.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Company")
public class Company {

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_seq")
	@SequenceGenerator(name = "company_seq", sequenceName = "company_seq_table")
	private Integer id;

	@Column(name = "companyName")
	@NotNull(message = "company name cannot be null ")
	@NotEmpty(message = "company name cannot be empty ")
	private String companyName;

	@Column(name = "createdOn")
	private Instant createdOn;

}
