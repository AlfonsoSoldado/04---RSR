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
import domain.Curriculum;
import domain.Ranger;
import domain.Trip;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class RangerServiceTest extends AbstractTest {

	// Service under test ---------------

	@Autowired
	private RangerService rangerService;

	// Supporting services --------------

	@Autowired
	private CurriculumService curriculumService;

	@Autowired
	private TripService tripService;

	// Test -----------------------------

	@Test
	public void testCreateRanger() {
		Ranger ranger;
		ranger = this.rangerService.create();
		Assert.notNull(ranger);
	}

	@Test
	public void testFindAllRanger() {
		Collection<Ranger> rangers;
		rangers = new ArrayList<Ranger>();
		rangers = this.rangerService.findAll();
		Assert.notNull(rangers);
	}

	@Test
	public void testFindOneRanger() {
		Ranger ranger;
		ranger = this.rangerService.findOne(super.getEntityId("ranger1"));
		Assert.notNull(ranger);
	}

	@Test
	public void testSaveRanger() {
		authenticate("ranger01");
		Ranger ranger;
		ranger = this.rangerService.create();
		Curriculum curriculum;
		Collection<Trip> trip = new ArrayList<Trip>();
		Trip trip1;

		curriculum = this.curriculumService.findOne(super
				.getEntityId("curriculum1"));
		trip1 = this.tripService.findOne(super.getEntityId("trip1"));
		trip.add(trip1);

		Boolean suspicious = false;

		ranger.setSuspicious(suspicious);
		ranger.setCurriculum(curriculum);
		ranger.setTrip(trip);
		ranger.setName("Juan");
		ranger.setSurname("Sanchez");
		ranger.setEmail("juanranger@hotmail.com");

		this.rangerService.save(ranger);
		unauthenticate();
	}

	@Test
	public void testDeleteRanger() {
		Ranger ranger;
		ranger = this.rangerService.findOne(super.getEntityId("ranger2"));
		this.rangerService.delete(ranger);
	}

	@Test
	public void testRangerSuspicious() {
		Collection<Ranger> suspicious;
		suspicious = new ArrayList<Ranger>();
		suspicious = this.rangerService.rangersSuspicious();
		Assert.notNull(suspicious);
	}

	@Test
	public void testFindByPrincipal() {
		authenticate("ranger01");
		Ranger ranger;
		ranger = this.rangerService.findByPrincipal();
		Assert.notNull(ranger);
		unauthenticate();
	}
}
