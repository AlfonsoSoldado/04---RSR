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
import domain.Ranger;
import domain.Trip;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
				"classpath:spring/datasource.xml",
				"classpath:spring/config/packages.xml"})
@Transactional
public class TripServiceTest extends AbstractTest{
	
	// Service under test ---------------
	
	@Autowired
	private TripService tripService;
	
	// Supporting services --------------
	@Autowired
	private ManagerService managerService;

	@Autowired
	private ActorService actorService;
	
	
	// Test -----------------------------
	
	@Test
	public void testCreateTrip(){
		Trip trip;
		trip = this.tripService.create();
		Assert.notNull(trip);
	}
	
	@Test
	public void testFindAllTrip(){
		Collection<Trip> trips;
		trips = new ArrayList<Trip>();
		trips = this.tripService.findAll();
		Assert.notNull(trips);
	}

	@Test
	public void testFindOneTrip(){
		Trip trip;
		trip = this.tripService.findOne(super.getEntityId("trip1"));
		Assert.notNull(trip);
	}
	
	@Test
	public void testSaveTrip(){
		Trip trip;
		trip = this.tripService.create();
		
		//attributes
		trip.setTicker("171042-JUHP");
		trip.setTitle("title");
		trip.setDescription("descripcion");
		trip.setPrice(40.5);
		
		Collection<String> requirements;
		requirements = new ArrayList<String>();
		String r1 = "requirement 1";
		String r2 = "requirement 2";
		requirements.add(r1);
		requirements.add(r2);
		trip.setRequirement(requirements);
		
		//TODO: mirar si peta las fechas
		Date d = new Date(System.currentTimeMillis()-1);
		trip.setPublication(d);
		
		Date d2 = new Date(System.currentTimeMillis()-3);
		trip.setTripStart(d2);
		
		Date d3 = new Date(System.currentTimeMillis()-2);
		trip.setTripEnd(d3);
		
		trip.setReason("reason");
		trip.setCancelled(false);
		
		//Relationships
		Ranger ranger;
		//ranger = this.rangerService.create();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
