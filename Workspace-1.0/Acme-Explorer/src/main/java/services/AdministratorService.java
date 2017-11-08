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
import domain.Message;
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
		Administrator res;
		UserAccount userAccount;
		Authority authority;
		Collection<SocialId> socialId;
		Collection<Message> message;
		
		res = new Administrator();
		userAccount = new UserAccount();
		authority = new Authority();
		socialId = new ArrayList<SocialId>();
		message = new ArrayList<Message>();
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
