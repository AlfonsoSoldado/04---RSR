package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RangerRepository;
import security.LoginService;
import security.UserAccount;
import domain.Manager;
import domain.Ranger;

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
	
	public Ranger findByPrincipal() {
		Ranger res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		res = this.rangerRepository.findRangerByUserAccountId(userAccount.getId());
		Assert.notNull(res);
		return res;
	}
}
