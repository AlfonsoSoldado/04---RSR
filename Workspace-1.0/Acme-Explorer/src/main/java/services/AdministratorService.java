package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.Authority;
import security.UserAccount;
import domain.Administrator;
import domain.Folder;
import domain.SocialId;

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
	
	public AdministratorService(){
		super();
	}
	
	// Simple CRUD methods
	
	public Administrator create() {
		Administrator res = new Administrator();
		UserAccount userAccount = new UserAccount();
		Authority authority = new Authority();
		Collection<SocialId> socialId = new ArrayList<SocialId>();
		Collection<Folder> folder = new ArrayList<Folder>();
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
}
