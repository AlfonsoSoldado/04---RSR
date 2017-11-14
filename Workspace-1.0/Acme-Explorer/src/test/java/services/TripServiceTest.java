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
	
	
	// Test -----------------------------
	
	@Test
	public void testCreateTrip(){
		Trip trip;
		trip = this.tripService.create();
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
		trip = this.tripService.create();
		
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
		trip.setRanger(ranger);
		
		Collection<Survival> survivals;
		survivals = new ArrayList<Survival>();
		trip.setSurvival(survivals);
		
		Manager manager;
		manager = this.managerService.create();
		trip.setManager(manager);
		
		Collection<Story> stories;
		stories = new ArrayList<Story>();
		trip.setStory(stories);
		
		Collection<Stage> stages;
		stages = new ArrayList<Stage>();
		Stage stage1;
		stage1 = new Stage();
		Stage stage2;
		stage2 = new Stage();
		stages.add(stage1);
		stages.add(stage2);
		trip.setStage(stages);
		
		Category category;
		category = new Category();
		trip.setCategory(category);
		
		LegalText legalText;
		legalText = new LegalText();
		trip.setLegalText(legalText);
		
		Collection<Application> applications;
		applications = new ArrayList<Application>();
		Application application1;
		application1 = new Application();
		Application application2;
		application2 = new Application();
		applications.add(application1);
		applications.add(application2);
		trip.setApplication(applications);
		
		Collection<Audit> audits;
		audits = new ArrayList<Audit>();
		trip.setAudit(audits);
		
		Collection<Note> notes;
		notes = new ArrayList<Note>();
		trip.setNote(notes);
		
		Collection<Value> values;
		values = new ArrayList<Value>();
		trip.setValue(values);
	}
	
	@Test
	public void testDeteleTrip(){
		Trip trip;
		trip = this.tripService.findOne(super.getEntityId("trip2"));
		this.tripService.delete(trip);
	}
	
	@Test
	public void testFindTripsByManager(){
		Collection<Trip> trips;
		trips = new ArrayList<Trip>();
		Integer intManager;
		intManager = super.getEntityId("manager1");
		trips.addAll(this.tripService.findTripsByManager(intManager));
		Assert.notNull(trips);
	}
	
	@Test
	public void testEditByManager(){
		Trip trip;
		trip = new Trip();
		Integer intTrip;
		intTrip = super.getEntityId("trip1");
		trip = this.tripService.editByManager(intTrip);
		Assert.notNull(trip);
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
		Category c = new Category();
		trips.addAll(this.tripService.findTripsByCategory(c));
	}
	
	
	@Test
	public void testCancelTrip(){
		Trip trip;
		trip = this.tripService.findOne(super.getEntityId("trip2"));
		this.tripService.cancelTrip(trip);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
