package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MiscellaneousRecordRepository;
import domain.MiscellaneousRecord;

@Service
@Transactional
public class MiscellaneousRecordService {

	// Managed repository

	@Autowired
	private MiscellaneousRecordRepository miscellaneousRecordRepository;

	// Supporting services

	// Constructors

	public MiscellaneousRecordService() {
		super();
	}

	// Simple CRUD methods

	public MiscellaneousRecord create() {
		MiscellaneousRecord res;

		res = new MiscellaneousRecord();
		return res;
	}

	public MiscellaneousRecord findOne(int miscellaneousRecord) {
		Assert.isTrue(miscellaneousRecord != 0);
		MiscellaneousRecord res;
		res = this.miscellaneousRecordRepository.findOne(miscellaneousRecord);
		Assert.notNull(res);
		return res;
	}

	public Collection<MiscellaneousRecord> findAll() {
		Collection<MiscellaneousRecord> res;

		res = this.miscellaneousRecordRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public MiscellaneousRecord save(MiscellaneousRecord miscellaneousRecord) {
		Assert.notNull(miscellaneousRecord);

		MiscellaneousRecord res;
		res = this.miscellaneousRecordRepository.save(miscellaneousRecord);
		return res;
	}

	public void delete(MiscellaneousRecord miscellaneousRecord) {
		Assert.notNull(miscellaneousRecord);
		Assert.isTrue(miscellaneousRecord.getId() != 0);
		Assert.isTrue(this.miscellaneousRecordRepository
				.exists(miscellaneousRecord.getId()));

		this.miscellaneousRecordRepository.delete(miscellaneousRecord);
	}

	// Other business methods

}
