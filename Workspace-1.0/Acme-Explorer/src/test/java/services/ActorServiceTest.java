package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.Actor;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
				"classpath:spring/datasource.xml",
				"classpath:spring/config/packages.xml"})
@Transactional
public class ActorServiceTest extends AbstractTest{
	
	// Serice under test -------------------------
	
	@Autowired
	private ActorService actorService;
	
	
	// Test --------------------------------------
	
	@Test
	public void testSaveActors(){
		Actor actor, saved;
		Collection<Actor> actors;
		
		//actor = actorService.create();
		//TODO: terminar
		
	}

}
