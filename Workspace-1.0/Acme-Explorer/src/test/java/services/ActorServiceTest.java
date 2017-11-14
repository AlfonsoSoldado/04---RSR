package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import security.UserAccount;
import utilities.AbstractTest;
import domain.Actor;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
				"classpath:spring/datasource.xml",
				"classpath:spring/config/packages.xml"})
@Transactional
public class ActorServiceTest extends AbstractTest{
	
	// Service under test -------------------------
	
	@Autowired
	private ActorService actorService;
	
	// Supporting services --------------
	@Autowired
	private TripService tripService;
	private UserAccount userAccountService;
	
	// Test --------------------------------------
	
	@Test
	public void testFindAllActor(){
		Collection<Actor> actors;
		actors = this.actorService.findAll();
		Assert.notNull(actors);
	}
	
	@Test
	public void testFindOneActor(){
		Actor actor;
		actor = this.actorService.findOne(super.getEntityId("administrator1"));
		Assert.notNull(actor);
	}

	@Test
	public void testSaveActor() {
		this.authenticate("admin");
		List<Actor> actor;
		actor = new ArrayList<>(this.actorService.findAll());
		Actor res;
		res = actor.get(0);
		res.setEmail("prueba@hotmal.es");
		this.actorService.save(res);
		unauthenticate();
	}
	
	@Test
	public void testDeleteActor(){
		Actor actor;
		actor = this.actorService.findOne(super.getEntityId("administrator1"));
		this.actorService.delete(actor);
	}
	
	//TODO
	@Test
	public void testCheckUserLogin(){
		
	}
	@Test
	public void testFindByPrincipal(){
		Actor actor;
		actor= this.actorService.findByPrincipal();
		Assert.notNull(actor);
	}
	
	//TODO
	@Test
	public void testCheckAuthority(){
		
	}
	
	
	
	
}
