package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ExplorerRepository;
import security.LoginService;
import security.UserAccount;
import domain.Auditor;
import domain.Explorer;

@Service
@Transactional
public class ExplorerService {

	// Managed repository

	@Autowired
	private ExplorerRepository explorerRepository;

	// Supporting services

	@Autowired
	private FolderService folderService;

	// Constructors

	public ExplorerService() {
		super();
	}

	// Simple CRUD methods

	public Collection<Explorer> findAll() {
		Collection<Explorer> res;
		res = this.explorerRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Explorer findOne(int explorer) {
		Assert.isTrue(explorer != 0);
		Explorer res;
		res = this.explorerRepository.findOne(explorer);
		Assert.notNull(res);
		return res;
	}

	public Explorer save(Explorer explorer) {
		Assert.notNull(explorer);
		Explorer res;
		res = this.explorerRepository.save(explorer);
		return res;
	}

	public void delete(Explorer explorer) {
		Assert.notNull(explorer);
		Assert.isTrue(explorer.getId() != 0);
		Assert.isTrue(this.explorerRepository.exists(explorer.getId()));
		this.explorerRepository.delete(explorer);
	}

	// Other business methods
	
	public Explorer findByPrincipal() {
		Explorer res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		res = this.explorerRepository.findExplorerByUserAccountId(userAccount.getId());
		Assert.notNull(res);
		return res;
	}
}
