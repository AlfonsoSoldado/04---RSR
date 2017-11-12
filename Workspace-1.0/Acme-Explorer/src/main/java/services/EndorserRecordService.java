package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EndorserRecordRepository;
import domain.EndorserRecord;

@Service
@Transactional
public class EndorserRecordService {
	
	// Managed repository

	@Autowired
	private EndorserRecordRepository endorserRecordRepository;
	
	// Supporting services

	// Constructors

	public EndorserRecordService() {
		super();
	}

	// Simple CRUD methods
	
	public EndorserRecord create(){
		EndorserRecord res;
		
		res = new EndorserRecord();
		return res;
	}

	public EndorserRecord findOne(int endorserRecord) {
		Assert.isTrue(endorserRecord != 0);
		EndorserRecord res;
		res = this.endorserRecordRepository.findOne(endorserRecord);
		Assert.notNull(res);
		return res;
	}
	
	public Collection<EndorserRecord> findAll() {
		Collection<EndorserRecord> res;
		res = this.endorserRecordRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public EndorserRecord save(EndorserRecord endorserRecord) {
		Assert.notNull(endorserRecord);
		
		EndorserRecord res;
		res = this.endorserRecordRepository.save(endorserRecord);
		return res;
	}

	public void delete(EndorserRecord endorserRecord) {
		Assert.notNull(endorserRecord);
		Assert.isTrue(endorserRecord.getId() != 0);
		Assert.isTrue(this.endorserRecordRepository.exists(endorserRecord.getId()));
		this.endorserRecordRepository.delete(endorserRecord);
	}

	// Other business methods

}
