package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PersonalRecordRepository;
import domain.PersonalRecord;

@Service
@Transactional
public class PersonalRecordService {

	// Managed repository

	@Autowired
	private PersonalRecordRepository personalRecordRepository;

	// Supporting services

	// Constructors

	public PersonalRecordService() {
		super();
	}

	// Simple CRUD methods
	
	public PersonalRecord create() {
		PersonalRecord personalRecord;
		personalRecord = new PersonalRecord();
		return personalRecord;
	}

	public Collection<PersonalRecord> findAll() {
		Collection<PersonalRecord> res;
		res = this.personalRecordRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public PersonalRecord findOne(int personalRecord) {
		Assert.isTrue(personalRecord != 0);
		PersonalRecord res;
		res = this.personalRecordRepository.findOne(personalRecord);
		Assert.notNull(res);
		return res;
	}

	public PersonalRecord save(PersonalRecord personalRecord) {
		Assert.notNull(personalRecord);
		PersonalRecord res;
		res = this.personalRecordRepository.save(personalRecord);
		return res;
	}

	public void delete(PersonalRecord personalRecord) {
		Assert.notNull(personalRecord);
		Assert.isTrue(personalRecord.getId() != 0);
		Assert.isTrue(this.personalRecordRepository.exists(personalRecord
				.getId()));
		this.personalRecordRepository.delete(personalRecord);
	}

	// Other business methods

}
