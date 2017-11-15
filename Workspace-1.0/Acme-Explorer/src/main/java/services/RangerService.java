package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RangerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Curriculum;
import domain.Folder;
import domain.Ranger;
import domain.SocialId;
import domain.Trip;

@Service
@Transactional
public class RangerService {

	// Managed repository

	@Autowired
	private RangerRepository rangerRepository;

	// Supporting services

	@Autowired
	private FolderService folderService;

	// Constructors

	public RangerService() {
		super();
	}

	// Simple CRUD methods

	public Ranger create() {
		Ranger res = new Ranger();

		UserAccount userAccount = new UserAccount();
		Authority authority = new Authority();
		Collection<SocialId> socialId = new ArrayList<SocialId>();
		Collection<Folder> folder = new ArrayList<Folder>();
		Curriculum curriculum = new Curriculum();
		Collection<Trip> trip = new ArrayList<Trip>();
		folder = this.folderService.systemFolders();

		authority.setAuthority(Authority.RANGER);
		userAccount.addAuthority(authority);
		res.setUserAccount(userAccount);
		res.setSocialId(socialId);
		res.setFolders(folder);
		res.setCurriculum(curriculum);
		res.setTrip(trip);
		res.setSuspicious(false);

		return res;
	}

	public Collection<Ranger> findAll() {
		Collection<Ranger> res;
		res = this.rangerRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Ranger findOne(int ranger) {
		Assert.isTrue(ranger != 0);
		Ranger res;
		res = this.rangerRepository.findOne(ranger);
		Assert.notNull(res);
		return res;
	}

	public Ranger save(Ranger ranger) {
		Assert.notNull(ranger);
		Ranger res;
		res = this.rangerRepository.save(ranger);
		return res;
	}

	public void delete(Ranger ranger) {
		Assert.notNull(ranger);
		Assert.isTrue(ranger.getId() != 0);
		Assert.isTrue(this.rangerRepository.exists(ranger.getId()));
		this.rangerRepository.delete(ranger);
	}

	// Other business methods

	// 35.1
	
	public Collection<Ranger> rangersSuspicious() {
		Collection<Ranger> res = new ArrayList<Ranger>();
		res.addAll(rangerRepository.rangersSuspicious());
		Assert.notNull(res);
		return res;
	}

	public Ranger findByPrincipal() {
		Ranger res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		res = this.rangerRepository.findRangerByUserAccountId(userAccount
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
		res.setAuthority("RANGER");
		Assert.isTrue(authority.contains(res));
	}
}
