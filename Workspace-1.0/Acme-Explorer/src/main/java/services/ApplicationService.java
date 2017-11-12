package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ApplicationRepository;
import domain.Application;
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
	// 12
	private ManagerService managerService;

	@Autowired
	// 13
	private ExplorerService explorerService;

	// Constructors

	public ApplicationService() {
		super();
	}

	// Simple CRUD methods

	public Application create() {
		Application res = new Application();
		String status;
		String comment = new String();
		status = "ACCEPTED";
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
		if (application.getStatus().equals("DUE") && application.getCreditCard() != null){
			res.setStatus("ACCEPTED");
		}
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
		 Manager m = this.managerService.findByPrincipal();
		 Assert.isTrue(m.getApplication().contains(a));
		 Assert.isTrue(a.getStatus().equals("PENDING"));
		 Assert.isTrue(status.equals("REJECTED") || status.equals("DUE"));
		 a.setStatus(status);
		 return a;
	 }

	// 12.2 (listing)
	public Collection<Application> findApplicationsByManager(int id) {
		Collection<Application> res = new ArrayList<Application>();
		Manager m = this.managerService.create();
		// comprobamos que la application seleccionada sea de este manager
		m = managerService.findByPrincipal();
		Assert.notNull(m);
		res.addAll(applicationRepository.findApplicationsByManager(id));
		Assert.notNull(res);
		return res;
	}

	// 13.2
	// TODO: falta lo de agruparlos por status
	public Collection<Application> findApplicationByExplorer(int id) {
		Collection<Application> res = new ArrayList<Application>();
		Explorer e = this.explorerService.create();
		// comprobamos que la application seleccionada sea de este explorer
		e = explorerService.findByPrincipal();
		Assert.notNull(e);
		// añadimos todas las application mediante la query
		res.addAll(applicationRepository.findApplicationByExplorer(id));
		Assert.notNull(res);
		return res;
	}
}
