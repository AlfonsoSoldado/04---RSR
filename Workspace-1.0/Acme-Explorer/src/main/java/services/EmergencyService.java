package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EmergencyRepository;
import domain.Emergency;

@Service
@Transactional
public class EmergencyService {

	// Managed repository

	@Autowired
	private EmergencyRepository emergencyRepository;

	// Supporting services

	// Constructors

	public EmergencyService() {
		super();
	}

	// Simple CRUD methods
	
	public Emergency create() {
		Emergency emergency;
		emergency = new Emergency();
		return emergency;
	}

	public Collection<Emergency> findAll() {
		Collection<Emergency> res;
		res = this.emergencyRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Emergency findOne(int emergency) {
		Assert.isTrue(emergency != 0);
		Emergency res;
		res = this.emergencyRepository.findOne(emergency);
		Assert.notNull(res);
		return res;
	}

	public Emergency save(Emergency emergency) {
		Assert.notNull(emergency);
		Emergency res;
		res = this.emergencyRepository.save(emergency);
		return res;
	}

	public void delete(Emergency emergency) {
		Assert.notNull(emergency);
		Assert.isTrue(emergency.getId() != 0);
		Assert.isTrue(this.emergencyRepository.exists(emergency.getId()));
		this.emergencyRepository.delete(emergency);
	}

	// Other business methods
	
}
