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
import domain.Finder;
import domain.Trip;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class FinderServiceTest extends AbstractTest {

	// Service under test ---------------

	@Autowired
	private FinderService finderService;

	// Supporting services --------------

	@Autowired
	private TripService tripService;

	// Test -----------------------------

	@Test
	public void testCreateFinder() {
		Finder finder;
		finder = this.finderService.create();
		Assert.notNull(finder);
	}

	@Test
	public void testFindOneFinder() {
		authenticate("explorer01");
		Finder finder;
		finder = this.finderService.findOne(super.getEntityId("finder1"));
		Assert.notNull(finder);
		unauthenticate();
	}

	@Test
	public void testFindAllFinder() {
		authenticate("explorer01");
		Collection<Finder> finders;
		finders = new ArrayList<Finder>();
		finders = this.finderService.findAll();
		Assert.notNull(finders);
		unauthenticate();
	}

	@Test
	public void testSaveFinder() {
		authenticate("explorer01");
		Finder finder;
		finder = this.finderService.create();

		finder.setSingleKey("Mexico");

		finder.setMinPrice(80.3);

		finder.setMaxPrice(407.4);

		Date start = new Date(System.currentTimeMillis() - 1);
		finder.setStart(start);

		Date end = new Date(System.currentTimeMillis() - 50);
		finder.setEnd(end);

		Date cache = new Date(System.currentTimeMillis() - 1);
		finder.setCache(cache);

		Collection<Trip> result = new ArrayList<Trip>();
		Trip trip;
		trip = tripService.findOne(super.getEntityId("trip1"));
		result.add(trip);
		finder.setTrip(result);

		this.finderService.save(finder);
		unauthenticate();
	}

	@Test
	public void testFindSearchCriterial() {
		Collection<Trip> trips;
		String singleKey = "Beach";
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2017);
		cal.set(Calendar.MONTH, 12);
		cal.set(Calendar.DAY_OF_MONTH, 12);
		Calendar cal2 = Calendar.getInstance();
		cal2.set(Calendar.YEAR, 2017);
		cal2.set(Calendar.MONTH, 12);
		cal2.set(Calendar.DAY_OF_MONTH, 20);
		Date start = cal.getTime();
		Date end = cal2.getTime();
		Double minPrice = 80.;
		Double maxPrice = 100.;

		trips = this.finderService.findSearchCriterial(singleKey, start, end,
				minPrice, maxPrice);
		Assert.notNull(trips);
	}
}
