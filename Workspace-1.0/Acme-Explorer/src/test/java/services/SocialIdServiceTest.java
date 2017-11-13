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

import domain.SocialId;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
				"classpath:spring/datasource.xml",
				"classpath:spring/config/packages.xml"})
@Transactional
public class SocialIdServiceTest extends AbstractTest{

	// Service under test ----------------
	
	@Autowired
	private SocialIdService socialIdService;
	
	
	// Supporting services ---------------
	
	// Test ------------------------------
	
	@Test
	public void testCreateSocialId(){
		SocialId socialId;
		socialId = this.socialIdService.create();
		Assert.notNull(socialId);
	}
	
	@Test
	public void testFindAllSocialId(){
		Collection<SocialId> social;
		social = new ArrayList<SocialId>();
		social = this.socialIdService.findAll();
		Assert.notNull(social);
	}
	
	@Test
	public void testFindOneSocialId(){
		SocialId socialId;
		socialId = this.socialIdService.findOne(super.getEntityId("socialId1"));
		Assert.notNull(socialId);
	}
	
	@Test
	public void testSaveSocialId(){
		SocialId socialId;
		socialId = this.socialIdService.create();
		
		socialId.setPhoto("http://www.imagendeejemplo1.com");
		
		socialId.setNick("Pepito");
		
		socialId.setNameSocialNetwork("Pepe Perez");
		
		socialId.setSocialNetwork("");
		
	}
	
	@Test
	public void testDeleteSociald(){
		SocialId socialId;
		socialId = this.socialIdService.findOne(super.getEntityId("socialId2"));
		this.socialIdService.delete(socialId);
	}
}
