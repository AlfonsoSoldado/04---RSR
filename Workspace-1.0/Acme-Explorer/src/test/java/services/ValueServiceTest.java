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
import domain.Tag;
import domain.Trip;
import domain.Value;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class ValueServiceTest extends AbstractTest {

	// Service under test ---------------

	@Autowired
	private ValueService valueService;

	// Supporting services --------------

	@Autowired
	private TripService tripService;

	@Autowired
	private TagService tagService;

	// Test -----------------------------

	@Test
	public void testCreateValue() {
		Value value;
		value = this.valueService.create();
		Assert.notNull(value);
	}

	@Test
	public void testFindAllValue() {
		Collection<Value> values;
		values = new ArrayList<Value>();
		values = this.valueService.findAll();
		Assert.notNull(values);
	}

	@Test
	public void testFindOneValue() {
		Value value;
		value = this.valueService.findOne(super.getEntityId("value1"));
		Assert.notNull(value);
	}

	@Test
	public void testSaveValue() {
		Value value;
		value = this.valueService.findOne(super.getEntityId("value1"));

		Trip trip;
		trip = this.tripService.findOne(super.getEntityId("trip1"));
		value.setTrip(trip);

		Tag tag;
		tag = this.tagService.findOne(super.getEntityId("tag1"));
		Collection<Tag> tags;
		tags = new ArrayList<Tag>();
		tags.add(tag);
		value.setTag(tags);

		this.valueService.save(value);
	}

	@Test
	public void testDeleteValue() {
		Value value;
		value = this.valueService.findOne(super.getEntityId("value2"));
		this.valueService.delete(value);
	}
}
