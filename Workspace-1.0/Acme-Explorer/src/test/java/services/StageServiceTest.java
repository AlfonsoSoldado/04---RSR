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
import domain.Stage;
import domain.Trip;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class StageServiceTest extends AbstractTest {

	// Service under test ---------------

	@Autowired
	private StageService stageService;

	// Supporting services --------------

	@Autowired
	private TripService tripService;

	// Test -----------------------------

	@Test
	public void testCreateStage() {
		Stage stage;
		stage = this.stageService.create();
		Assert.notNull(stage);
	}

	@Test
	public void testFindAllRanger() {
		Collection<Stage> stages;
		stages = new ArrayList<Stage>();
		stages = this.stageService.findAll();
		Assert.notNull(stages);
	}

	@Test
	public void testFindOneStage() {
		Stage stage;
		stage = this.stageService.findOne(super.getEntityId("stage1"));
		Assert.notNull(stage);
	}

	@Test
	public void testSaveStage() {
		Stage stage;
		stage = this.stageService.findOne(super.getEntityId("stage1"));
		Trip trip;
		trip = this.tripService.findOne(super.getEntityId("trip1"));

		stage.setTitle("First");
		stage.setDescription("Something");
		stage.setPrice(528.4);
		stage.setTrip(trip);

		this.stageService.save(stage);
	}

	@Test
	public void testDeleteStage() {
		Stage stage;
		stage = this.stageService.findOne(super.getEntityId("stage2"));
		this.stageService.delete(stage);
	}
}
