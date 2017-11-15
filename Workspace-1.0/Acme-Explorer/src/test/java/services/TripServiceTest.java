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
	private RangerService rangerServices;
	private CategoryService categoryServices;
	private LegalTextService legalTextService;
	private ApplicationService applicationService;
	private SurvivalService survivalService;
	private StoryService storyService;
	private StageService stageService;
	private AuditService auditService;
	private NoteService noteService;
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
		Trip trip;
		trip = this.tripService.findOne(super.getEntityId("trip1"));
		
		//attributes
		trip.setTicker("171042-JUHP");
		trip.setTitle("title");
		trip.setDescription("descripcion");
		trip.setPrice(40.5);
		
		Collection<String> requirements;
		requirements = new ArrayList<String>();
		String r1 = "requirement 1";
		String r2 = "requirement 2";
		requirements.add(r1);
		requirements.add(r2);
		trip.setRequirement(requirements);
		
		//TODO: mirar si peta las fechas
		Date d = new Date(System.currentTimeMillis()-1);
		trip.setPublication(d);
		
		Date d2 = new Date(System.currentTimeMillis()-3);
		trip.setTripStart(d2);
		
		Date d3 = new Date(System.currentTimeMillis()-2);
		trip.setTripEnd(d3);
		
		trip.setReason("reason");
		trip.setCancelled(false);
		
		//Relationships
		Ranger ranger;
		ranger = new Ranger();
		ranger= this.rangerServices.findOne(super.getEntityId("ranger1"));
		trip.setRanger(ranger);
		
		Collection<Survival> survivals;
		survivals = new ArrayList<Survival>();
		Survival survival1;
		survival1= this.survivalService.findOne(super.getEntityId("survival1"));
		Survival survival2;
		survival2= this.survivalService.findOne(super.getEntityId("survival2"));
		
		survivals.add(survival1);
		survivals.add(survival2);
		trip.setSurvival(survivals);
		
		Manager manager;
		manager = this.managerService.findOne(super.getEntityId("manager1"));
		trip.setManager(manager);
		
		Collection<Story> stories;
		stories = new ArrayList<Story>();
		Story story1;
		story1= this.storyService.findOne(super.getEntityId("story1"));
		
		stories.add(story1);
		trip.setStory(stories);
		
		Collection<Stage> stages;
		stages = new ArrayList<Stage>();
		Stage stage1;
		stage1 = this.stageService.findOne(super.getEntityId("stage1"));
		Stage stage2;
		stage2 = this.stageService.findOne(super.getEntityId("stage2"));
		stages.add(stage1);
		stages.add(stage2);
		trip.setStage(stages);
		
		Category category;
		category = this.categoryServices.findOne(super.getEntityId("category1"));
		trip.setCategory(category);
		
		LegalText legalText;
		legalText = this.legalTextService.findOne(super.getEntityId("legatText1"));;
		trip.setLegalText(legalText);
		
		Collection<Application> applications;
		applications = new ArrayList<Application>();
		Application application1;
		application1 = this.applicationService.findOne(super.getEntityId("application1"));
		Application application2;
		application2 = this.applicationService.findOne(super.getEntityId("application2"));
		applications.add(application1);
		applications.add(application2);
		trip.setApplication(applications);
		
		Collection<Audit> audits;
		audits = new ArrayList<Audit>();
		Audit audit;
		audit= this.auditService.findOne(super.getEntityId("audit1"));
		audits.add(audit);
		trip.setAudit(audits);
		
		Collection<Note> notes;
		notes = new ArrayList<Note>();
		Note note;
		note= this.noteService.findOne(super.getEntityId("note1"));
		notes.add(note);
		trip.setNote(notes);
		
		Collection<Value> values;
		values = new ArrayList<Value>();
		Value value1;
		value1= this.valueService.findOne(super.getEntityId("value1"));
		values.add(value1);
		trip.setValue(values);
		
		this.tripService.save(trip);
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
		Category c = this.categoryServices.findOne(super.getEntityId("category1"));
		Assert.notNull(c);
		
		trips.addAll(this.tripService.findTripsByCategory(c));
	}
	
	
	@Test
	public void testCancelTrip(){
		
		authenticate("manager01");
		Trip trip;
		trip = this.tripService.findOne(super.getEntityId("trip2"));
		Assert.notNull(trip);
		this.tripService.cancelTrip(trip);
		
		unauthenticate();
	}
	
	@Test
	// TODO: hacer testTripApplicationExplorer
	public void testTripApplicationExplorer(){
		authenticate("manager01");
		Trip trip;
		trip = this.tripService.findOne(super.getEntityId("trip2"));
		this.tripService.tripApplicationExplorer(trip);
		unauthenticate();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
