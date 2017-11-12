package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Application;
import domain.Explorer;
import domain.Manager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
				"classpath:spring/datasource.xml",
				"classpath:spring/config/packages.xml"})
@Transactional
public class ApplicationServiceTest extends AbstractTest {

	// Service under test -------------------------
	
	@Autowired
	private ApplicationService applicationService;
			
	// Supporting services -----------------------
			
	@Autowired
	private ExplorerService explorerService;
	
	@Autowired
	private ManagerService managerService;
			
	// Test --------------------------------------
	
	//Application explorer
	
	@Test
	public void testCreateApplicationExplorer() {
		authenticate("explorer01");
		Application application;
		application = this.applicationService.create();
		Assert.notNull(application);
		unauthenticate();
	}
	
	@Test
	public void testFindAllApplicationExplorer() {
		authenticate("explorer01");
		Collection<Application> applications;
		applications = this.applicationService.findAll();
		Assert.notNull(applications);
	}
	
	@Test
	public void testFindOneApplicationExplorer() {
		authenticate("explorer01");
		Application application;
		application = this.applicationService.findOne(super.getEntityId("application1"));
		Assert.notNull(application);
	}
	
	@Test
	public void testSaveApplicationExplorer() {
		this.authenticate("explorer01");
		Application application;
		application = this.applicationService.create();
		
		Explorer explorer = this.explorerService.create();
		explorer.setAddress("C/ Bolonia");
		explorer.setEmail("otroemail@gmail.com");
		explorer.setName("ExplorerNuevo");
		explorer.setApplication(application);
		
		application.setExplorer(explorer);
		application.setComment("comentario de un explorer");
		application.setReason("Estaba cabreado");
		
		Manager manager = this.managerService.create();
		manager.setAddress("C/ Domingo");
		manager.setEmail("alberto@gmail.com");
		manager.setName("Alberto");
		Collection<Application> applicationManager = manager.getApplication();
		applicationManager.add(application);
		manager.setApplication(applicationManager);
		
		this.applicationService.save(application);
		unauthenticate();
	}
	
	@Test
	public void testDeleteApplicationExplorer() {
		authenticate("explorer01");
		Application application;
		application = this.applicationService.findOne(super.getEntityId("application1"));
		this.applicationService.delete(application);
	}
	
	
	// Application manager
	
	
	@Test
	public void testCreateApplicationManager() {
		authenticate("manager01");
		Application application;
		application = this.applicationService.create();
		Assert.notNull(application);
		unauthenticate();
	}
	
	@Test
	public void testFindAllApplicationManager() {
		authenticate("manager01");
		Collection<Application> applications;
		applications = this.applicationService.findAll();
		Assert.notNull(applications);
	}
	
	@Test
	public void testFindOneApplicationManager() {
		authenticate("manager01");
		Application application;
		application = this.applicationService.findOne(super.getEntityId("application1"));
		Assert.notNull(application);
	}
	
	@Test
	public void testSaveApplicationManager() {
		this.authenticate("manager01");
		Application application;
		application = this.applicationService.create();
		
		Explorer explorer = this.explorerService.create();
		explorer.setAddress("C/ Italia");
		explorer.setEmail("otroemailmas@gmail.com");
		explorer.setName("ManagerNuevo");
		explorer.setApplication(application);
		
		application.setExplorer(explorer);
		application.setComment("comentario de un manager");
		application.setReason("Mala atención");
		
		Manager manager = this.managerService.create();
		manager.setAddress("C/ Viernes");
		manager.setEmail("francisco@gmail.com");
		manager.setName("Francisco");
		Collection<Application> applicationManager = manager.getApplication();
		applicationManager.add(application);
		manager.setApplication(applicationManager);
		
		this.applicationService.save(application);
		unauthenticate();
	}
	
	@Test
	public void testDeleteApplicationManager() {
		authenticate("manager01");
		Application application;
		application = this.applicationService.findOne(super.getEntityId("application1"));
		this.applicationService.delete(application);
	}
}
