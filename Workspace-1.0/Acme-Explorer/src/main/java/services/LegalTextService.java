package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.LegalTextRepository;
import domain.LegalText;
import domain.Trip;

@Service
@Transactional
public class LegalTextService {

	// Managed repository

	@Autowired
	private LegalTextRepository legalTextRepository;

	// Supporting services

	@Autowired
	private AdministratorService administratorService;
	
	// Constructors

	public LegalTextService() {
		super();
	}

	// Simple CRUD methods
	
	public LegalText create() {
		administratorService.checkAuthority();
		
		LegalText res = new LegalText();
		Date moment = new Date(System.currentTimeMillis() - 1);
		Trip trip = new Trip();
		res.setDraftMode(true);
		res.setTrip(trip);
		res.setMoment(moment);
		return res;
	}

	public Collection<LegalText> findAll() {
		administratorService.checkAuthority();
		
		Collection<LegalText> res;
		res = this.legalTextRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public LegalText findOne(int legalText) {
		administratorService.checkAuthority();
		
		Assert.isTrue(legalText != 0);
		LegalText res;
		res = this.legalTextRepository.findOne(legalText);
		Assert.notNull(res);
		return res;
	}

	public LegalText save(LegalText legalText) {
		administratorService.checkAuthority();
		
		Assert.notNull(legalText);
		LegalText res;
		res = this.legalTextRepository.save(legalText);
		Assert.isTrue(res.getDraftMode() == true);
		return res;
	}

	public void delete(LegalText legalText) {
		administratorService.checkAuthority();
		
		Assert.notNull(legalText);
		Assert.isTrue(legalText.getId() != 0);
		Assert.isTrue(this.legalTextRepository.exists(legalText.getId()));
		Assert.isTrue(legalText.getDraftMode() == true);
		this.legalTextRepository.delete(legalText);
	}

	// Other business methods

}
