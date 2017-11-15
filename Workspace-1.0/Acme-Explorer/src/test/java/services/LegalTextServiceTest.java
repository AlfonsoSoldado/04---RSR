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
import domain.LegalText;
import domain.Trip;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class LegalTextServiceTest extends AbstractTest {

	// Service under test -----------

	@Autowired
	private LegalTextService legalTextService;

	// Supporting services ----------

	@Autowired
	private TripService tripService;

	// Test -------------------------

	@Test
	public void testCreateLegalText() {
		authenticate("admin");
		LegalText text;
		text = this.legalTextService.create();
		Assert.notNull(text);
		unauthenticate();
	}

	@Test
	public void testFindAllLegalText() {
		authenticate("admin");
		Collection<LegalText> texts;
		texts = new ArrayList<LegalText>();
		texts = this.legalTextService.findAll();
		Assert.notNull(texts);
		unauthenticate();
	}

	@Test
	public void testFindOneLegalText() {
		authenticate("admin");
		LegalText text;
		text = this.legalTextService.findOne(super.getEntityId("legalText1"));
		Assert.notNull(text);
		unauthenticate();
	}

	@Test
	public void testSaveLegalText() {
		authenticate("admin");
		LegalText text;
		text = this.legalTextService.create();

		text.setTitle("Sample Title");

		text.setBody("Sample body");

		text.setNumberLaw(10);

		Date moment = new Date(System.currentTimeMillis() - 1);
		text.setMoment(moment);

		text.setDraftMode(true);

		Trip trip;
		trip = tripService.findOne(super.getEntityId("trip1"));
		text.setTrip(trip);

		this.legalTextService.save(text);
		unauthenticate();
	}

	@Test
	public void testDeleteLegalText() {
		authenticate("admin");
		LegalText text;
		text = this.legalTextService.findOne(super.getEntityId("legalText2"));
		this.legalTextService.delete(text);
		unauthenticate();
	}

	@Test
	public void testFindLegalTextByTrip() {
		authenticate("admin");
		Collection<LegalText> texts;
		Trip trip;

		texts = new ArrayList<LegalText>();
		trip = this.tripService.findOne(super.getEntityId("trip1"));

		texts = this.legalTextService.findLegalTextsByTrip(trip);
		Assert.notNull(texts);
		unauthenticate();
	}
}
