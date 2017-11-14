package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.CC;
import domain.Sponsor;
import domain.Sponsorship;
import domain.Trip;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
				"classpath:spring/datasource.xml",
				"classpath:spring/config/packages.xml"})
@Transactional
public class SponsorshipServiceTest extends AbstractTest{

	
	// Service under test ---------------
	
	@Autowired
	private SponsorshipService sponsorshipService;
	
	// Supporting services --------------
	
	private SponsorService sponsorService;
	private TripService tripService;
	
	
	// Test -----------------------------
	
	@Test
	public void testCreateSponsorship(){
		
		Sponsorship sponsorship;
		sponsorship=this.sponsorshipService.create();
		Assert.notNull(sponsorship);
		
	}
	
	@Test
	public void testFindOneponsorship(){
		
		Sponsorship sponsorship;
		sponsorship= this.sponsorshipService.findOne(super.getEntityId("sponsorship1"));
		Assert.notNull(sponsorship);
		
	}
	
	@Test
	public void testSaveSponsorship(){
		Sponsorship sponsorship;
		sponsorship= this.sponsorshipService.create();
		
		
		
		sponsorship.setBanner("http://www.example.com");
		sponsorship.setInfoPage("http://www.example.es");
		
		CC creditCard;
		creditCard = new CC();
		sponsorship.setCreditCard(creditCard);
		
		Sponsor sponsor;
		sponsor=this.sponsorService.create();
		sponsorship.setSponsor(sponsor);
		
		Trip trip;
		trip= this.tripService.create();
		sponsorship.setTrip(trip);
	}
	
	@Test
	public void testDeleteSponsorship(){
		Sponsorship sponsorship;
		sponsorship=this.sponsorshipService.findOne(super.getEntityId("sponsorship1"));
		this.sponsorshipService.delete(sponsorship);
	}
}
