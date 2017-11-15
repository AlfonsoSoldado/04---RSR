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
import domain.Explorer;
import domain.Survival;
import domain.Trip;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class SurvivalServiceTest extends AbstractTest {

	// Service under test -------------------------

	@Autowired
	private SurvivalService survivalService;

	// Supporting services -----------------------

	@Autowired
	private TripService tripService;

	@Autowired
	private ExplorerService explorerService;

	// Test --------------------------------------

	@Test
	public void testCreateSurvival() {
		authenticate("manager01");
		Survival survival;
		survival = this.survivalService.create();
		Assert.notNull(survival);
		unauthenticate();
	}

	@Test
	public void testFindAllSurvival() {
		Collection<Survival> survival;
		survival = this.survivalService.findAll();
		Assert.notNull(survival);
	}

	@Test
	public void testFindOneSurvival() {
		Survival survival;
		survival = this.survivalService.findOne(super.getEntityId("survival1"));
		Assert.notNull(survival);
	}

	@Test
	public void testSaveSurvival() {
		Survival survival;
		Trip trip;

		survival = this.survivalService.findOne(super.getEntityId("survival1"));
		trip = this.tripService.findOne(super.getEntityId("trip1"));
		survival.setTrip(trip);

		this.survivalService.save(survival);
	}

	@Test
	public void testDeleteSurvival() {
		Survival survival;
		survival = this.survivalService.findOne(super.getEntityId("survival1"));
		this.survivalService.delete(survival);
	}

	@Test
	public void testFindSurvivalByTrips() {
		authenticate("manager01");
		Collection<Survival> survivals;

		survivals = new ArrayList<Survival>();
		Survival survival;
		survival = this.survivalService.findOne(super.getEntityId("survival1"));
		survivals.add(survival);
		survivals = this.survivalService.findSurvivalByTrips();

		survivals = new ArrayList<Survival>();
		survivals.addAll(this.survivalService.findSurvivalByTrips());

		Assert.notNull(survivals);
		unauthenticate();
	}

	@Test
	public void testFindOneByTrips() {
		authenticate("manager01");
		Survival survival, res;
		int id;

		survival = this.survivalService.findOne(super.getEntityId("survival1"));
		id = survival.getId();

		res = this.survivalService.findOneByTrips(id);
		Assert.notNull(res);
		unauthenticate();
	}

	@Test
	public void testSaveByTrips() {
		Survival survival;
		Trip trip;

		survival = this.survivalService.findOne(super.getEntityId("survival2"));
		trip = this.tripService.findOne(super.getEntityId("trip2"));
		survival.setTrip(trip);

		this.survivalService.save(survival);
	}

	@Test
	public void testByDeleteTrips() {
		authenticate("manager01");
		Survival survival;
		survival = this.survivalService.findOne(super.getEntityId("survival1"));
		this.survivalService.deleteByTrips(survival);
		unauthenticate();
	}

	@Test
	public void testEnrolSurvival() {
		authenticate("explorer02");
		Explorer explorer;
		Survival survival;

		explorer = this.explorerService.findOne(super.getEntityId("explorer2"));
		survival = this.survivalService.findOne(super.getEntityId("survival2"));

		this.survivalService.enrolSurvival(explorer, survival);
		unauthenticate();
	}
}
