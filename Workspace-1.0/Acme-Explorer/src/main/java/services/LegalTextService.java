package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.LegalTextRepository;
import domain.LegalText;

@Service
@Transactional
public class LegalTextService {

	// Managed repository

	@Autowired
	private LegalTextRepository legalTextRepository;

	// Supporting services

	// Constructors

	public LegalTextService() {
		super();
	}

	// Simple CRUD methods

	public Collection<LegalText> findAll() {
		Collection<LegalText> res;
		res = this.legalTextRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public LegalText findOne(int legalText) {
		Assert.isTrue(legalText != 0);
		LegalText res;
		res = this.legalTextRepository.findOne(legalText);
		Assert.notNull(res);
		return res;
	}

	public LegalText save(LegalText legalText) {
		Assert.notNull(legalText);
		LegalText res;
		res = this.legalTextRepository.save(legalText);
		return res;
	}

	public void delete(LegalText legalText) {
		Assert.notNull(legalText);
		Assert.isTrue(legalText.getId() != 0);
		Assert.isTrue(this.legalTextRepository.exists(legalText.getId()));
		this.legalTextRepository.delete(legalText);
	}

	// Other business methods

}
