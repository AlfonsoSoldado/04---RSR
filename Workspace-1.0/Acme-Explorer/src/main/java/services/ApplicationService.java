package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ApplicationRepository;
import domain.Application;
import domain.CC;
import domain.Explorer;
import domain.Manager;

@Service
@Transactional
public class ApplicationService {

	// Managed repository

	@Autowired
	private ApplicationRepository applicationRepository;

	// Supporting services

	@Autowired
	private ManagerService managerService;

	@Autowired
	private ExplorerService explorerService;

	// Constructors

	public ApplicationService() {
		super();
	}

	// Simple CRUD methods

	public Application create() {
		Application res = new Application();
		
		String status;
		status = "ACCEPTED";
		String comment = new String();
		
		res.setStatus(status);
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

	// 12.2 (changing)
	
	 public void changingStatus(Application a, String status) {
		 managerService.checkAuthority();
		 Assert.notNull(a);
		 Assert.notNull(status);
		 Assert.isTrue(status.equals("REJECTED") || status.equals("DUE"));
		 
		 Manager m = this.managerService.findByPrincipal();
		 
		 Assert.notNull(m);
		 
		 Collection<Application> applications = new ArrayList<Application>();
		 applications = applicationRepository.findListApplicationPending(m.getId());
		 
		 if(applications.contains(a)){
			 a.setStatus(status);
		 }
	 }

	// 12.2 (listing)
	 
	public Collection<Application> findListApplication(Manager manager) {
		managerService.checkAuthority();
		Collection<Application> res = new ArrayList<Application>();
		
		res.addAll(applicationRepository.findListApplication(manager.getId()));
		Assert.notNull(res);
		
		return res;
	}

	// 13.2
	
	public Collection<Application> findApplicationByExplorer(Explorer explorer) {
		explorerService.checkAuthority();
		Collection<Application> res = new ArrayList<Application>();
		
		res.addAll(applicationRepository.findApplicationByExplorer(explorer.getId()));
		Assert.notNull(res);
		
		return res;
	}
	
	// 13.3
	
	public void applicationAccepted(CC creditCard, Application application){
		explorerService.checkAuthority();
		Assert.notNull(creditCard);
		Assert.notNull(application);
		
		Collection<Application> applications = new ArrayList<>();
		applications = applicationRepository.findListApplicationDue();
		
		if(applications.contains(application)){
			application.setStatus("ACCEPTED");
			application.setCreditCard(creditCard);
		}
	}
}
