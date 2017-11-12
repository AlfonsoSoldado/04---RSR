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
import domain.Folder;
import domain.Manager;
import domain.Message;
import domain.SocialId;
import domain.Survival;
import domain.Trip;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
				"classpath:spring/datasource.xml",
				"classpath:spring/config/packages.xml"})
@Transactional
public class ManagerServiceTest extends AbstractTest {
	
	// Service under test -------------------------
	
	@Autowired
	private ManagerService managerService;
	
	// Supporting services -----------------------
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private SocialIdService socialIdService;
	
	@Autowired
	private SurvivalService survivalService;
	
	@Autowired
	private TripService tripService;
	
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private FolderService folderService;
	
	// Test --------------------------------------
	
	@Test
	public void testCreateManager() {
		Manager manager;
		manager = this.managerService.create();
		Assert.notNull(manager);
	}
	
	@Test
	public void testFindAllManager() {
		Collection<Manager> managers;
		managers = this.managerService.findAll();
		Assert.notNull(managers);
	}
	
	@Test
	public void testFindOneManager() {
		Manager manager;
		manager = this.managerService.findOne(super.getEntityId("manager1"));
		Assert.notNull(manager);
	}
	
	@Test
	public void testSaveManager() {
		Manager manager;
		manager = this.managerService.create();
		
		manager.setName("Pedro");
		manager.setPhoneNumber("653123456");
		
		Message received;
		received = this.messageService.create();
		manager.setReceived(received);
		
		Message sent;
		sent = this.messageService.create();
		Collection<Message> sents = manager.getSent();
		sents.add(sent);
		manager.setSent(sents);
		
		SocialId socialId;
		socialId = this.socialIdService.create();
		Collection<SocialId> socialIds = manager.getSocialId();
		socialIds.add(socialId);
		manager.setSocialId(socialIds);
		
		manager.setSurname("Cano");
		
		Survival survival;
		survival = this.survivalService.create();
		Collection<Survival> survivals = manager.getSurvival();
		survivals.add(survival);
		manager.setSurvival(survivals);
		
		manager.setSuspicious(false);
		
		Trip trip;
		trip = this.tripService.create();
		Collection<Trip> trips = manager.getTrip();
		trips.add(trip);
		manager.setTrip(trips);
		
		manager.setAddress("C/ Castilla");
		
		Application application;
		application = this.applicationService.create();
		Collection<Application> applications = manager.getApplication();
		applications.add(application);
		manager.setApplication(applications);
		
		manager.setEmail("managerimportante@gmail.com");
		
		Folder customFolder;
		customFolder = this.folderService.create();
		Collection<Folder> folders = manager.getFolders();
		folders.add(customFolder);
		manager.setFolders(folders);
		
		this.managerService.save(manager);
	}
}
