package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ApplicationRepository;
import domain.Application;

@Service
@Transactional
public class ApplicationService {
	
	// Managed repository

	@Autowired
	private ApplicationRepository applicationRepository;
	
	// Supporting services
	
	// Constructors
	
	public ApplicationService(){
		super();
	}
	
	// Simple CRUD methods
	
	public Application create() {
		Application res;
		//String status;
		String comment;
		res = new Application();
		//status = "ACCEPTED";
		comment = new String();
		//res.setStatus(status);
		res.setComment(comment);
		return res;
	}
	
	public Collection<Application> findAll() {
		Collection<Application> res;
		res = this.applicationRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	
	public Application findOne(int applicationId) {
		Assert.isTrue(applicationId != 0);
		Application res;
		res = this.applicationRepository.findOne(applicationId);
		Assert.notNull(res);
		return res;
	}
	
	public Application save(Application application) {
		Assert.notNull(application);
		Application res;
		res = this.applicationRepository.save(application);
		return res;
	}
	
	public void delete(Application application) {
		Assert.notNull(application);
		Assert.isTrue(application.getId() != 0);
		Assert.isTrue(this.applicationRepository.exists(application.getId()));
		this.applicationRepository.delete(application);
	}
	
	// Other business methods
	
}
