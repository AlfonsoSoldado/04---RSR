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
import domain.Administrator;
import domain.SocialId;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class SocialIdServiceTest extends AbstractTest {

	// Service under test ----------------

	@Autowired
	private SocialIdService socialIdService;

	// Supporting services ---------------

	@Autowired
	private AdministratorService administratorService;

	// Test ------------------------------

	@Test
	public void testCreateSocialId() {
		authenticate("admin");
		SocialId socialId;
		socialId = this.socialIdService.create();
		Assert.notNull(socialId);
		unauthenticate();
	}

	@Test
	public void testFindAllSocialId() {
		Collection<SocialId> social;
		social = new ArrayList<SocialId>();
		social = this.socialIdService.findAll();
		Assert.notNull(social);
	}

	@Test
	public void testFindOneSocialId() {
		SocialId socialId;
		socialId = this.socialIdService.findOne(super.getEntityId("socialId1"));
		Assert.notNull(socialId);
	}

	@Test
	public void testSaveSocialId() {
		authenticate("admin");
		SocialId socialId;
		socialId = this.socialIdService.create();

		socialId.setPhoto("http://www.imagendeejemplo1.com");

		socialId.setNick("Pepito");

		Administrator admin;
		admin = administratorService.findOne(super
				.getEntityId("administrator1"));

		socialId.setActor(admin);

		socialId.setNameSocialNetwork("Pepito Grillo");

		socialId.setSocialNetwork("http://www.pepitogrillo.com");

		this.socialIdService.save(socialId);
		unauthenticate();
	}

	@Test
	public void testDeleteSociald() {
		SocialId socialId;
		socialId = this.socialIdService.findOne(super.getEntityId("socialId2"));
		this.socialIdService.delete(socialId);
	}
}
