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
import domain.Finder;
import domain.Trip;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
				"classpath:spring/datasource.xml",
				"classpath:spring/config/packages.xml"})
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
	public void testCreateFinder(){
		Finder finder;
		finder = this.finderService.create();
		Assert.notNull(finder);
	}
	
	@Test
	public void testFindOneFinder(){
		Finder finder;
		finder = this.finderService.findOne(super.getEntityId("finder1"));
		Assert.notNull(finder);
	}
	
	@Test
	public void testFindAllFinder(){
		Collection<Finder> finders;
		finders = new ArrayList<Finder>();
		finders = this.finderService.findAll();
		Assert.notNull(finders);
	}
	
	@Test
	public void testSaveFinder(){
		Finder finder;
		finder = this.finderService.create();
		
		finder.setSingleKey("Mexico");
		
		finder.setMinPrice(80.3);
		
		finder.setMaxPrice(407.4);
		
		Date start = new Date(System.currentTimeMillis() - 1);
		finder.setStart(start);
		
		Date end = new Date(System.currentTimeMillis() -50);
		finder.setEnd(end);
		
		Collection<Trip> result = new ArrayList<Trip>();
		Trip trip;
		trip = tripService.create();
		result.add(trip);
		finder.setTrip(result);
		
		this.finderService.save(finder);
	}
	
	@Test
	// TODO: testEditByExplorer
	public void testEditByExplorer(){
		
	}
	
	@Test
	// TODO: testFindSearchCriterial
	public void testFindSearchCriterial(){
		
	}
	
	
}
