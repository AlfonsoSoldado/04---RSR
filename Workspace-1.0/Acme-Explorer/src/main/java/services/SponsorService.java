package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SponsorRepository;
import domain.Sponsor;

@Service
@Transactional
public class SponsorService {

	// Managed repository

	@Autowired
	private SponsorRepository sponsorRepository;

	// Supporting services

	@Autowired
	private FolderService folderService;

	// Constructors

	public SponsorService() {
		super();
	}

	// Simple CRUD methods

	public Collection<Sponsor> findAll() {
		Collection<Sponsor> res;
		res = this.sponsorRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Sponsor findOne(int sponsor) {
		Assert.isTrue(sponsor != 0);
		Sponsor res;
		res = this.sponsorRepository.findOne(sponsor);
		Assert.notNull(res);
		return res;
	}

	public Sponsor save(Sponsor sponsor) {
		Assert.notNull(sponsor);
		Sponsor res;
		res = this.sponsorRepository.save(sponsor);
		return res;
	}

	public void delete(Sponsor sponsor) {
		Assert.notNull(sponsor);
		Assert.isTrue(sponsor.getId() != 0);
		Assert.isTrue(this.sponsorRepository.exists(sponsor.getId()));
		this.sponsorRepository.delete(sponsor);
	}

	// Other business methods

}
