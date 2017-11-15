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
import domain.Audit;
import domain.Category;
import domain.Curriculum;
import domain.Folder;
import domain.Message;
import domain.Ranger;
import domain.Trip;


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
	private CategoryService categoryService;
	private RangerService rangerService;
	private MessageService messageService;
	private FolderService folderService;
	
	
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
	
	@Test
	public void testCheckUserLogin(){
		
		
		
	}
	@Test
	public void testFindByPrincipal(){
		Actor actor;
		actor= this.actorService.findByPrincipal();
		Assert.notNull(actor);
	}
	
	
	
	@Test
	public void testBrowseAllTrips(){
		Collection<Trip> res;
		res= new ArrayList<Trip>();
		res= this.actorService.browseAllTrips();
		Assert.notNull(res);
	}
	
	@Test
	public void testSearchTripsBySingleKey(){
		
		String key;
		key="Punta Cana";
		Assert.notNull(key);
		
		Collection<Trip> res;
		res= new ArrayList<Trip>();
		res = this.actorService.searchTripsBySingleKey(key);
		Assert.notNull(res);
	}
	
	@Test 
	public void testSearchTripsByCategory(){
		Category category;
		category= this.categoryService.findOne(super.getEntityId("category1"));
		Assert.notNull(category);
		
		Collection<Trip> res;
		res= new ArrayList<Trip>();
		res= this.actorService.searchTripsByCategory(category);
		Assert.notNull(res);
		
	}
	
	@Test
	public void testFindCurriculumRangerByTrip(){
		
		int i;
		Ranger ranger;
		ranger= this.rangerService.findOne(super.getEntityId("Ranger1"));
		Assert.notNull(ranger);
		i= ranger.getId();
		
		
		Collection<Curriculum> res;
		res= new ArrayList<Curriculum>();
		res=this.actorService.findCurriculumRangerByTrip(i);
		Assert.notNull(res);
		
	}
	
	@Test
	public void testFindAuditByTrip(){
		int i;
		Trip trip;
		trip= this.tripService.findOne(super.getEntityId("Trip2"));
		Assert.notNull(trip);
		i=trip.getId();
		
		Collection<Audit> res;
		res=new ArrayList<Audit>();
		res=this.actorService.findAuditByTrip(i);
		Assert.notNull(res);
		
	}
	
	@Test
	public void testBanActor(){
		Actor actor;
		actor= this.actorService.findOne(super.getEntityId("actor1"));
		Assert.notNull(actor);
		
		this.actorService.banActor(actor);
	}
	
	@Test
	public void testUnBanActor(){
		Actor actor;
		actor= this.actorService.findOne(super.getEntityId("actor2"));
		Assert.notNull(actor);
		
		this.actorService.unbanActor(actor);
		
	}
	
	@Test
	public void testSendNotificationBroadcast(){
		Message message;
		message= this.messageService.findOne(super.getEntityId("message1"));
		Assert.notNull(message);
		
		this.actorService.SendNotificationBroadcast(message);
	}
	
	@Test
	public void testCheckSpamWords(){
		this.actorService.checkSpamWords();
	}
	
	@Test
	public void testActorToSuspiciousList(){
		this.actorService.actorToSuspiciousList();
	}
	
	@Test
	public void testSendMessage(){
		Collection<Actor> actores;
		actores= new ArrayList<Actor>();
		Assert.notNull(actores);
				
		Actor sender;
		sender=this.actorService.findOne(super.getEntityId("actor2"));
		Assert.notNull(sender);
		
		Message message;
		message= this.messageService.findOne(super.getEntityId("message2"));
		Assert.notNull(message);
		
		this.actorService.sendMessage(actores, sender, message);
		
	}
	
	@Test
	public void testDeleteMessage(){
		
		Actor actor;
		actor=this.actorService.findOne(super.getEntityId("actor1"));
		Assert.notNull(actor);
		
		Message message;
		message= this.messageService.findOne(super.getEntityId("message1"));
		Assert.notNull(message);
		
		this.actorService.deleteMessage(actor, message);
		
		
	}
	
	@Test
	public void testMoveMessage(){
		
		Message message;
		message= this.messageService.findOne(super.getEntityId("message1"));
		Assert.notNull(message);
		
		Actor actor;
		actor=this.actorService.findOne(super.getEntityId("actor1"));
		Assert.notNull(actor);
		
		Folder folder;
		folder= this.folderService.findOne(super.getEntityId("folder1"));
		Assert.notNull(folder);
		
		this.actorService.moveMessage(message, actor, folder);
		
	}
	
	
	
	
	
	
	
}
