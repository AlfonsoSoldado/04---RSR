package services;

import java.util.ArrayList;
import java.util.Calendar;
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
import domain.Audit;
import domain.Category;
import domain.LegalText;
import domain.Manager;
import domain.Note;
import domain.Ranger;
import domain.Stage;
import domain.Story;
import domain.Survival;
import domain.Trip;
import domain.Value;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
				"classpath:spring/datasource.xml",
				"classpath:spring/config/packages.xml"})
@Transactional
public class TripServiceTest extends AbstractTest{
	
	// Service under test ---------------
	
	@Autowired
	private TripService tripService;
	
	// Supporting services --------------
	@Autowired
	private ManagerService managerService;
	@Autowired
	private RangerService rangerServices;
	@Autowired
	private CategoryService categoryServices;
	@Autowired
	private LegalTextService legalTextService;
	@Autowired
	private ApplicationService applicationService;
	@Autowired
	private SurvivalService survivalService;
	@Autowired
	private StoryService storyService;
	@Autowired
	private StageService stageService;
	@Autowired
	private AuditService auditService;
	@Autowired
	private NoteService noteService;
	@Autowired
	private ValueService valueService;
	
	
	// Test -----------------------------
	
	@Test
	public void testCreateTrip(){
		Trip trip;
		trip = this.tripService.findOne(super.getEntityId("trip1"));
		Assert.notNull(trip);
	}
	
	@Test
	public void testFindAllTrip(){
		Collection<Trip> trips;
		trips = new ArrayList<Trip>();
		trips = this.tripService.findAll();
		Assert.notNull(trips);
	}

	@Test
	public void testFindOneTrip(){
		Trip trip;
		trip = this.tripService.findOne(super.getEntityId("trip1"));
		Assert.notNull(trip);
	}
	
	@Test
	public void testSaveTrip(){
		//authenticate("admin");
		authenticate("manager01");
		Trip trip;
		trip = this.tripService.findOne(super.getEntityId("trip1"));
		
		trip.setTitle("Funciona");
		
	
		this.tripService.save(trip);
		unauthenticate();
	}
	
	@Test
	public void testDeteleTrip(){
		authenticate("manager01");
		Trip trip;
		trip = this.tripService.findOne(super.getEntityId("trip1"));
		Assert.notNull(trip);
		this.tripService.delete(trip);
		unauthenticate();
		
	}
	
	@Test
	public void testFindTripsByManager(){
		authenticate("manager01");
		Collection<Trip> trips;
		trips = new ArrayList<Trip>();
		Trip trip;
		trip=this.tripService.findOne(super.getEntityId("trip2"));
		Assert.notNull(trip);
		trips.add(trip);
		
		Manager manager;
		manager= this.managerService.findOne(super.getEntityId("manager2"));
		Assert.notNull(manager);
		
		Integer intManager;
		intManager = manager.getId();
		trips.addAll(this.tripService.findTripsByManager(intManager));
		Assert.notNull(trips);
		
		unauthenticate();
	}
	
	@Test
	public void testEditByManager(){
		authenticate("manager01");
		Trip trip;
		trip = new Trip();
		Integer intTrip;
		intTrip = super.getEntityId("trip1");
		trip = this.tripService.editByManager(intTrip);
		Assert.notNull(trip);
		unauthenticate();
	}
	
	@Test
	public void testFindTripsByExplorer(){
		Collection<Trip> trips;
		trips = new ArrayList<Trip>();
		Integer intExplorer;
		intExplorer = super.getEntityId("explorer1");
		trips.addAll(this.tripService.findTripsByExplorer(intExplorer));
		Assert.notNull(trips);
	}
	
	@Test
	public void testBrowseTripsByActor(){
		Collection<Trip> trips;
		trips = new ArrayList<Trip>();
		trips = this.tripService.browseTripsByActor();
		Assert.notNull(trips);
	}
	
	@Test
	public void testFindTrips(){
		Collection<Trip> trips;
		trips = new ArrayList<Trip>();
		trips = this.tripService.findTrips("trip1");
		Assert.notNull(trips);
	}
	
	//TODO: revisar
	@Test
	public void testFindTripsByCategory(){
		Collection<Trip> trips;
		trips = new ArrayList<Trip>();
		Category c = categoryServices.findOne(super.getEntityId("category1"));
		Assert.notNull(c);
		
		trips.addAll(tripService.findTripsByCategory(c));
	}
	
	
	@Test
	public void testCancelTrip(){
		
		authenticate("manager01");
		Trip trip;
		trip = this.tripService.findOne(super.getEntityId("trip1"));
		Assert.notNull(trip);
		this.tripService.cancelTrip(trip);
		
		unauthenticate();
	}
	
	@Test
	public void testTripApplicationExplorer(){
		authenticate("explorer01");
		Trip trip;
		trip = this.tripService.findOne(super.getEntityId("trip2"));
		this.tripService.tripApplicationExplorer(trip);
		unauthenticate();
	}
}
