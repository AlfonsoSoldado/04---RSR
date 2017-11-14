package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Application;
import domain.CC;
import domain.Explorer;
import domain.Manager;
import domain.Trip;

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
	
	@Autowired
	private TripService tripService;
	
	
			
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
		
		Manager manager;
		manager = this.managerService.create();
		manager.setAddress("C/ Jueves");
		manager.setEmail("fernan@gmail.com");
		manager.setName("Fernando");
		Collection<Application> applicationManager = manager.getApplication();
		applicationManager.add(application);
		manager.setApplication(applicationManager);
		application.setManager(manager);
		
		Date moment = new Date(System.currentTimeMillis() - 1);
		application.setMoment(moment);
		
		application.setReason("Muy mala comida");
		application.setStatus("DUE");
		
		Trip trip;
		trip = this.tripService.create();
		application.setTrip(trip);
		
		application.setComment("comentario de un explorer");
		
		CC creditCard;
		creditCard = new CC();
		application.setCreditCard(creditCard);
		
		Explorer explorer;
		explorer = this.explorerService.create();
		explorer.setAddress("C/ Venecia");
		explorer.setEmail("otromas@gmail.com");
		explorer.setName("Gonzalo");
		explorer.setApplication(application);
		application.setExplorer(explorer);
		
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
		
		Manager manager;
		manager = this.managerService.create();
		manager.setAddress("C/ Viernes");
		manager.setEmail("francisco@gmail.com");
		manager.setName("Francisco");
		Collection<Application> applicationManager = manager.getApplication();
		applicationManager.add(application);
		manager.setApplication(applicationManager);
		application.setManager(manager);
		
		Date moment = new Date(System.currentTimeMillis() - 1);
		application.setMoment(moment);
		
		application.setReason("Mala atención");
		application.setStatus("DUE");
		
		Trip trip;
		trip = this.tripService.create();
		application.setTrip(trip);
		
		application.setComment("comentario de un manager");
		
		CC creditCard;
		creditCard = new CC();
		application.setCreditCard(creditCard);
		
		Explorer explorer;
		explorer = this.explorerService.create();
		explorer.setAddress("C/ Italia");
		explorer.setEmail("otroemailmas@gmail.com");
		explorer.setName("ManagerNuevo");
		explorer.setApplication(application);
		application.setExplorer(explorer);
		
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
	
	@Test
	public void testFindListApplication(){
		
		Manager manager;
		manager= this.managerService.findOne(super.getEntityId("manager1"));
		Assert.notNull(manager);
		
		Collection<Application> res = new ArrayList<Application>();
		res= this.applicationService.findListApplication(manager);
		Assert.notNull(res);
	}
	
	@Test
	public void testFindApplicationByExplorer(){
		
		int id;
		Explorer explorer;
		explorer= this.explorerService.findOne(super.getEntityId("Explorer1"));
		Assert.notNull(explorer);
		
		Collection<Application> res= new ArrayList<Application>();
		res= this.applicationService.findApplicationByExplorer(explorer.getId());
		Assert.notNull(explorer);
	}
	
	@Test
	public void testApplicationAccepted(){
		CC cc;
		cc= new CC();
		cc.setHolderName("BBVA");
		cc.setBrandName("MasterCard");
		cc.setNumber("4099537775843795");
		cc.setExpirationMonth(02);
		cc.setExpirationYear(2019);
		cc.setCVV(123);
		Assert.notNull(cc);
		
		Application application;
		application= this.applicationService.findOne(super.getEntityId("application2"));
		Assert.notNull(application);
		
		this.applicationService.applicationAccepted(cc, application);
		
	}
}
