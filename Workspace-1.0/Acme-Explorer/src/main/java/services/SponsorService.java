package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SponsorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Folder;
import domain.SocialId;
import domain.Sponsor;
import domain.Sponsorship;

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

	public Sponsor create() {
		Sponsor res = new Sponsor();
		UserAccount userAccount = new UserAccount();
		Authority authority = new Authority();
		Collection<SocialId> socialId = new ArrayList<SocialId>();
		Collection<Folder> folder = new ArrayList<Folder>();
		Collection<Sponsorship> sponsorship = new ArrayList<Sponsorship>();
		folder = this.folderService.systemFolders();
		res.setSocialId(socialId);
		res.setFolders(folder);
		authority.setAuthority(Authority.SPONSOR);
		userAccount.addAuthority(authority);
		res.setUserAccount(userAccount);
		res.setSponsorship(sponsorship);
		return res;
	}

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

	public Sponsor findByPrincipal() {
		Sponsor res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		res = this.sponsorRepository.findSponsorByUserAccountId(userAccount
				.getId());
		Assert.notNull(res);
		return res;
	}

	public void checkAuthority() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		Collection<Authority> authority = userAccount.getAuthorities();
		Assert.notNull(authority);
		Authority res = new Authority();
		res.setAuthority("SPONSOR");
		Assert.isTrue(authority.contains(res));
	}
}
