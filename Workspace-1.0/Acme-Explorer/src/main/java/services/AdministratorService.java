package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import domain.Folder;
import domain.SocialId;
import domain.Trip;

@Service
@Transactional
public class AdministratorService {

	// Managed repository

	@Autowired
	private AdministratorRepository administratorRepository;

	// Supporting services

	@Autowired
	private FolderService folderService;

	// Constructors

	public AdministratorService() {
		super();
	}

	// Simple CRUD methods

	public Administrator create() {
		Administrator res = new Administrator();

		UserAccount userAccount = new UserAccount();
		Authority authority = new Authority();
		Collection<SocialId> socialId = new ArrayList<SocialId>();
		Collection<Folder> folder = new ArrayList<Folder>();
		folder = this.folderService.systemFolders();

		authority.setAuthority(Authority.ADMIN);
		userAccount.addAuthority(authority);
		res.setUserAccount(userAccount);
		res.setSocialId(socialId);
		res.setFolders(folder);
		return res;
	}

	public Collection<Administrator> findAll() {
		Collection<Administrator> res;
		res = this.administratorRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Administrator findOne(int administratorId) {
		Assert.isTrue(administratorId != 0);
		Administrator res;
		res = this.administratorRepository.findOne(administratorId);
		Assert.notNull(res);
		return res;
	}

	public Administrator save(Administrator administrator) {
		checkAuthority();
		Assert.notNull(administrator);
		Administrator res;
		res = this.administratorRepository.save(administrator);
		return res;
	}

	public void delete(Administrator administrator) {
		Assert.notNull(administrator);
		Assert.isTrue(administrator.getId() != 0);
		Assert.isTrue(this.administratorRepository.exists(administrator.getId()));
		this.administratorRepository.delete(administrator);
	}

	// Other business methods

	public Administrator findByPrincipal() {
		Administrator res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		res = this.administratorRepository
				.findAdministratorByUserAccountId(userAccount.getId());
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
		res.setAuthority("ADMIN");
		Assert.isTrue(authority.contains(res));
	}

	public Double applicationPending() {
		Double res;
		res = this.administratorRepository.applicationPending();
		return res;
	}

	public Double applicationDue() {
		Double res;
		res = this.administratorRepository.applicationDue();
		return res;
	}

	public Double applicationAccepted() {
		Double res;
		res = this.administratorRepository.applicationAccepted();
		return res;
	}

	public Double applicationCancelled() {
		Double res;
		res = this.administratorRepository.applicationCancelled();
		return res;
	}

	public Double ratioRangerEndorser() {
		Double res;
		res = this.administratorRepository.ratioRangerEndorser();
		return res;
	}

	public Object[] avgMinMaxSqtrManager() {
		Object[] res;
		res = this.administratorRepository.avgMinMaxSqtrManager();
		return res;
	}

	public Double ratioManagerSuspicious() {
		Double res;
		res = this.administratorRepository.ratioManagerSuspicious();
		return res;
	}

	public Object[] avgMinMaxSqtrRanger() {
		Object[] res;
		res = this.administratorRepository.avgMinMaxSqtrRanger();
		return res;
	}

	public Double ratioRangerCurriculum() {
		Double res;
		res = this.administratorRepository.ratioRangerCurriculum();
		return res;
	}

	public Double ratioSuspiciousRanger() {
		Double res;
		res = this.administratorRepository.ratioSuspiciousRanger();
		return res;
	}

	public Object[] avgMinMaxSqtr() {
		Object[] res;
		res = this.administratorRepository.avgMinMaxSqtr();
		return res;
	}

	public Object[] avgMinMaxSqtr2() {
		Object[] res;
		res = this.administratorRepository.avgMinMaxSqtr2();
		return res;
	}

	public Double ratioTripsCancelled() {
		Double res;
		res = this.administratorRepository.ratioTripsCancelled();
		return res;
	}

	public Collection<Trip> tripsThanAverage() {
		Collection<Trip> res;
		res = this.administratorRepository.tripsThanAverage();
		return res;
	}

	public Double tripsLegalTextReferenced() {
		Double res;
		res = this.administratorRepository.tripsLegalTextReferenced();
		return res;
	}

	public Object[] avgMinMaxSqtr3() {
		Object[] res;
		res = this.administratorRepository.avgMinMaxSqtr3();
		return res;
	}

	public Object[] avgMinMaxSqtr4() {
		Object[] res;
		res = this.administratorRepository.avgMinMaxSqtr4();
		return res;
	}

	public Object[] avgMinMaxSqtr5() {
		Object[] res;
		res = this.administratorRepository.avgMinMaxSqtr5();
		return res;
	}
}
