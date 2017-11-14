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
	 public Application changingStatus(Application a, String status) {
		 Assert.notNull(a);
		 Assert.notNull(status);
		 
		 managerService.checkAuthority();
		 Manager m = this.managerService.findByPrincipal();
		 
		 Assert.isTrue(m.getApplication().contains(a));
		 Assert.isTrue(a.getStatus().equals("PENDING"));
		 Assert.isTrue(status.equals("REJECTED") || status.equals("DUE"));
		 
		 a.setStatus(status);
		 return a;
	 }

	// 12.2 (listing)
	public Collection<Application> findListApplication(int id) {
		Collection<Application> res = new ArrayList<Application>();
		
		Manager m = this.managerService.create();
		
		m = managerService.findByPrincipal();
		managerService.checkAuthority();
		
		Assert.notNull(m);
		
		res.addAll(applicationRepository.findListApplication(id));
		Assert.notNull(res);
		
		return res;
	}

	// 13.2
	public Collection<Application> findApplicationByExplorer(int id) {
		Collection<Application> res = new ArrayList<Application>();
		
		Explorer e = this.explorerService.create();
		
		e = explorerService.findByPrincipal();
		explorerService.checkAuthority();
		
		Assert.notNull(e);
		
		res.addAll(applicationRepository.findApplicationByExplorer(id));
		Assert.notNull(res);
		
		return res;
	}
	
	// 13.3
	public void applicationAccepted(CC creditCard, Application application){
		Assert.isTrue(application.getStatus().equals("DUE"));
		Assert.notNull(creditCard);
		application.setStatus("ACCEPTED");
		application.setCreditCard(creditCard);
	}
}
