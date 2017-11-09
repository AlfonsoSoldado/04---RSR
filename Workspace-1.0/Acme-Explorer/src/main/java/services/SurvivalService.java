package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SurvivalRepository;
import domain.Survival;

@Service
@Transactional
public class SurvivalService {

	// Managed repository

	@Autowired
	private SurvivalRepository survivalRepository;

	// Supporting services

	// Constructors

	public SurvivalService() {
		super();
	}

	// Simple CRUD methods

	public Collection<Survival> findAll() {
		Collection<Survival> res;
		res = this.survivalRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Survival findOne(int survival) {
		Assert.isTrue(survival != 0);
		Survival res;
		res = this.survivalRepository.findOne(survival);
		Assert.notNull(res);
		return res;
	}

	public Survival save(Survival survival) {
		Assert.notNull(survival);
		Survival res;
		res = this.survivalRepository.save(survival);
		return res;
	}

	public void delete(Survival survival) {
		Assert.notNull(survival);
		Assert.isTrue(survival.getId() != 0);
		Assert.isTrue(this.survivalRepository.exists(survival.getId()));
		this.survivalRepository.delete(survival);
	}

	// Other business methods

}
