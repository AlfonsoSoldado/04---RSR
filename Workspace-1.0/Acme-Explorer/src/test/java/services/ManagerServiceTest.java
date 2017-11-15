package services;

import java.util.ArrayList;
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
import domain.Manager;
import domain.Survival;
import domain.Trip;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class ManagerServiceTest extends AbstractTest {

	// Service under test -------------------------

	@Autowired
	private ManagerService managerService;

	// Supporting services -----------------------

	@Autowired
	private SurvivalService survivalService;

	@Autowired
	private TripService tripService;

	@Autowired
	private ApplicationService applicationService;

	// Test --------------------------------------

	@Test
	public void testCreateManager() {
		authenticate("admin");
		Manager manager;
		manager = this.managerService.create();
		Assert.notNull(manager);
		unauthenticate();
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
		authenticate("admin");
		Manager manager;
		manager = this.managerService.create();

		Collection<Survival> survival = new ArrayList<Survival>();
		Collection<Trip> trip = new ArrayList<Trip>();
		Application application;
		Survival survival1;
		Trip trip1;

		trip1 = this.tripService.findOne(super.getEntityId("trip1"));
		application = this.applicationService.findOne(super
				.getEntityId("application1"));
		survival1 = this.survivalService
				.findOne(super.getEntityId("survival1"));
		survival.add(survival1);
		trip.add(trip1);
		Boolean suspicious = false;

		manager.setName("Manuel");
		manager.setSurname("Perez");
		manager.setEmail("manuelp@hotmail.com");
		manager.setSuspicious(suspicious);
		manager.setSurvival(survival);
		manager.setTrip(trip);
		manager.setApplication(application);

		this.managerService.save(manager);
		unauthenticate();
	}

	@Test
	public void testDeleteManager() {
		Manager manager;
		manager = this.managerService.findOne(super.getEntityId("manager1"));
		this.managerService.delete(manager);
	}

	@Test
	public void testFindByPrincipalManager() {
		authenticate("manager01");
		Manager manager;
		manager = this.managerService.findByPrincipal();
		Assert.notNull(manager);
		unauthenticate();
	}
}
