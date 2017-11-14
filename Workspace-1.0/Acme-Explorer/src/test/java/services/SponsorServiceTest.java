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
import domain.Sponsor;
import domain.Sponsorship;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class SponsorServiceTest extends AbstractTest {

	// Service under test ---------------

	@Autowired
	private SponsorService sponsorService;

	// Supporting services --------------

	private SponsorshipService sponsorShipService;

	// Test -----------------------------

	@Test
	public void testCreateSponsor() {
		Sponsor sponsor;
		sponsor = this.sponsorService.create();
		Assert.notNull(sponsor);
	}

	@Test
	public void testFindOneSponsor() {
		Sponsor sponsor;
		sponsor = this.sponsorService.findOne(super.getEntityId("sponsor1"));
		Assert.notNull(sponsor);
	}

	@Test
	public void testSaveSponsor() {

		Sponsor sponsor;
		sponsor = this.sponsorService.create();

		Sponsorship sponsorShip;
		sponsorShip = this.sponsorShipService.create();
		Collection<Sponsorship> sponsorShips;
		sponsorShips = new ArrayList<Sponsorship>();
		sponsorShips.add(sponsorShip);
		sponsor.setSponsorship(sponsorShips);

	}

	@Test
	public void testDeleteSponsor() {
		Sponsor sponsor;
		sponsor = this.sponsorService.findOne(super.getEntityId("ranger2"));
		this.sponsorService.delete(sponsor);

	}

	// TODO
	@Test
	public void testFindByPrincipal() {

	}

	// TODO
	@Test
	public void testCheckAuthority() {

	}

}
