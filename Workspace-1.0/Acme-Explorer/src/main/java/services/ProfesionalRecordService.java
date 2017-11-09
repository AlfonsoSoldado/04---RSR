package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ProfesionalRecordRepository;
import domain.ProfessionalRecord;

@Service
@Transactional
public class ProfesionalRecordService {

	// Managed repository

	@Autowired
	private ProfesionalRecordRepository profesionalRecordRepository;

	// Supporting services

	// Constructors

	public ProfesionalRecordService() {
		super();
	}

	// Simple CRUD methods

	public Collection<ProfessionalRecord> findAll() {
		Collection<ProfessionalRecord> res;
		res = this.profesionalRecordRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public ProfessionalRecord findOne(int profesionalRecord) {
		Assert.isTrue(profesionalRecord != 0);
		ProfessionalRecord res;
		res = this.profesionalRecordRepository.findOne(profesionalRecord);
		Assert.notNull(res);
		return res;
	}

	public ProfessionalRecord save(ProfessionalRecord profesionalRecord) {
		Assert.notNull(profesionalRecord);
		ProfessionalRecord res;
		res = this.profesionalRecordRepository.save(profesionalRecord);
		return res;
	}

	public void delete(ProfessionalRecord profesionalRecord) {
		Assert.notNull(profesionalRecord);
		Assert.isTrue(profesionalRecord.getId() != 0);
		Assert.isTrue(this.profesionalRecordRepository.exists(profesionalRecord
				.getId()));
		this.profesionalRecordRepository.delete(profesionalRecord);
	}

	// Other business methods

}
