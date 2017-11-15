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
import domain.Category;
import domain.LegalText;
import domain.Manager;
import domain.Ranger;
import domain.Stage;
import domain.Story;
import domain.Survival;
import domain.Trip;
import domain.Value;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class TripServiceTest extends AbstractTest {

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
	private ValueService valueService;

	// Test -----------------------------

	@Test
	public void testCreateTrip() {
		Trip trip;
		trip = this.tripService.findOne(super.getEntityId("trip1"));
		Assert.notNull(trip);
	}

	@Test
	public void testFindAllTrip() {
		Collection<Trip> trips;
		trips = new ArrayList<Trip>();
		trips = this.tripService.findAll();
		Assert.notNull(trips);
	}

	@Test
	public void testFindOneTrip() {
		Trip trip;
		trip = this.tripService.findOne(super.getEntityId("trip1"));
		Assert.notNull(trip);
	}

	@Test
	public void testSaveTrip() {
		authenticate("admin");
		Trip trip;
		trip = this.tripService.create();

		Collection<Survival> survival = new ArrayList<Survival>();
		Collection<Story> story = new ArrayList<Story>();
		Collection<Stage> stage = new ArrayList<Stage>();
		Collection<Application> application = new ArrayList<Application>();
		Collection<Value> value = new ArrayList<Value>();
		Ranger ranger;
		Survival survival1;
		Manager manager;
		Story story1;
		Stage stage1;
		Category category;
		LegalText legalText;
		Application application1;
		Value value1;

		String ticker, title, description, reason, req1;
		Double price;
		Collection<String> requirement = new ArrayList<String>();
		Date publication, tripStart, tripEnd;
		Boolean cancelled = false;

		Calendar pu = Calendar.getInstance();
		pu.set(Calendar.YEAR, 2017);
		pu.set(Calendar.MONTH, 2);
		pu.set(Calendar.DAY_OF_MONTH, 13);
		Calendar start = Calendar.getInstance();
		start.set(Calendar.YEAR, 2017);
		start.set(Calendar.MONTH, 6);
		start.set(Calendar.DAY_OF_MONTH, 13);
		Calendar end = Calendar.getInstance();
		end.set(Calendar.YEAR, 2017);
		end.set(Calendar.MONTH, 6);
		end.set(Calendar.DAY_OF_MONTH, 22);

		publication = pu.getTime();
		tripStart = start.getTime();
		tripEnd = end.getTime();

		ticker = "171020-JUHE";
		title = "Beach";
		description = "Something";
		price = 80.;
		req1 = "Sample";
		requirement.add(req1);
		reason = "Reason";

		ranger = this.rangerServices.findOne(super.getEntityId("ranger1"));
		survival1 = this.survivalService
				.findOne(super.getEntityId("survival1"));
		survival.add(survival1);
		manager = this.managerService.findOne(super.getEntityId("manager1"));
		story1 = this.storyService.findOne(super.getEntityId("story1"));
		story.add(story1);
		stage1 = this.stageService.findOne(super.getEntityId("stage1"));
		stage.add(stage1);
		category = this.categoryServices.findOne(super.getEntityId("CATEGORY"));
		legalText = this.legalTextService.findOne(super
				.getEntityId("legalText1"));
		application1 = this.applicationService.findOne(super
				.getEntityId("application1"));
		application.add(application1);
		value1 = this.valueService.findOne(super.getEntityId("value1"));
		value.add(value1);

		trip.setTicker(ticker);
		trip.setTitle(title);
		trip.setDescription(description);
		trip.setPrice(price);
		trip.setRequirement(requirement);
		trip.setPublication(publication);
		trip.setTripStart(tripStart);
		trip.setTripEnd(tripEnd);
		trip.setReason(reason);
		trip.setCancelled(cancelled);
		trip.setRanger(ranger);
		trip.setSurvival(survival);
		trip.setManager(manager);
		trip.setStory(story);
		trip.setStage(stage);
		trip.setCategory(category);
		trip.setLegalText(legalText);
		trip.setApplication(application);
		trip.setValue(value);

		this.tripService.save(trip);
		unauthenticate();
	}

	@Test
	public void testDeteleTrip() {
		authenticate("manager01");
		Trip trip;
		trip = this.tripService.findOne(super.getEntityId("trip1"));
		Assert.notNull(trip);
		this.tripService.delete(trip);
		unauthenticate();
	}

	@Test
	public void testFindTripsByManager() {
		authenticate("manager01");
		Collection<Trip> trips;
		trips = new ArrayList<Trip>();
		Trip trip;
		trip = this.tripService.findOne(super.getEntityId("trip2"));
		Assert.notNull(trip);
		trips.add(trip);

		Manager manager;
		manager = this.managerService.findOne(super.getEntityId("manager2"));
		Assert.notNull(manager);

		Integer intManager;
		intManager = manager.getId();
		trips.addAll(this.tripService.findTripsByManager(intManager));
		Assert.notNull(trips);

		unauthenticate();
	}

	@Test
	public void testEditByManager() {
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
	public void testFindTripsByExplorer() {
		Collection<Trip> trips;
		trips = new ArrayList<Trip>();
		Integer intExplorer;
		intExplorer = super.getEntityId("explorer1");
		trips.addAll(this.tripService.findTripsByExplorer(intExplorer));
		Assert.notNull(trips);
	}

	@Test
	public void testBrowseTripsByActor() {
		Collection<Trip> trips;
		trips = new ArrayList<Trip>();
		trips = this.tripService.browseTripsByActor();
		Assert.notNull(trips);
	}

	@Test
	public void testFindTrips() {
		Collection<Trip> trips;
		trips = new ArrayList<Trip>();
		trips = this.tripService.findTrips("trip1");
		Assert.notNull(trips);
	}

	@Test
	public void testFindTripsByCategory() {
		Collection<Trip> trips;
		trips = new ArrayList<Trip>();
		Category c = categoryServices.findOne(super.getEntityId("category1"));
		Assert.notNull(c);

		trips.addAll(tripService.findTripsByCategory(c));
	}

	@Test
	public void testCancelTrip() {
		authenticate("manager01");
		Trip trip;
		trip = this.tripService.findOne(super.getEntityId("trip1"));
		Assert.notNull(trip);
		this.tripService.cancelTrip(trip);

		unauthenticate();
	}

	@Test
	public void testTripApplicationExplorer() {
		authenticate("explorer01");
		Trip trip;
		trip = this.tripService.findOne(super.getEntityId("trip2"));
		this.tripService.tripApplicationExplorer(trip);
		unauthenticate();
	}
}
