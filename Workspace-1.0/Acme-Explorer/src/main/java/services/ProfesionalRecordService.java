package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ProfesionalRecordRepository;
import domain.ProfesionalRecord;

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

	public Collection<ProfesionalRecord> findAll() {
		Collection<ProfesionalRecord> res;
		res = this.profesionalRecordRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public ProfesionalRecord findOne(int profesionalRecord) {
		Assert.isTrue(profesionalRecord != 0);
		ProfesionalRecord res;
		res = this.profesionalRecordRepository.findOne(profesionalRecord);
		Assert.notNull(res);
		return res;
	}

	public ProfesionalRecord save(ProfesionalRecord profesionalRecord) {
		Assert.notNull(profesionalRecord);
		ProfesionalRecord res;
		res = this.profesionalRecordRepository.save(profesionalRecord);
		return res;
	}

	public void delete(ProfesionalRecord profesionalRecord) {
		Assert.notNull(profesionalRecord);
		Assert.isTrue(profesionalRecord.getId() != 0);
		Assert.isTrue(this.profesionalRecordRepository.exists(profesionalRecord
				.getId()));
		this.profesionalRecordRepository.delete(profesionalRecord);
	}

	// Other business methods

}
