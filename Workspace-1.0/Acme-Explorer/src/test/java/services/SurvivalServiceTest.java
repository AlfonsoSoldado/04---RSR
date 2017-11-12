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
import domain.Manager;
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
		survival = new Survival();
		Trip trip = new Trip();
		survival.setTrip(trip);
	}
	
	@Test
	public void testDeleteSurvival() {
		Survival survival;
		survival = this.survivalService.findOne(super.getEntityId("survival1"));
		this.survivalService.delete(survival);
	}
}
