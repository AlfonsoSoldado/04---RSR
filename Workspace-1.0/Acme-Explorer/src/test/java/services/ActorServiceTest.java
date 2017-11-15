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

import utilities.AbstractTest;
import domain.Actor;
import domain.Audit;
import domain.Category;
import domain.Curriculum;
import domain.Folder;
import domain.Message;
import domain.Trip;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class ActorServiceTest extends AbstractTest {

	// Service under test -------------------------

	@Autowired
	private ActorService actorService;

	// Supporting services --------------
	@Autowired
	private TripService tripService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private FolderService folderService;

	@Autowired
	private CategoryService categoryService;

	// Test --------------------------------------

	@Test
	public void testFindAllActor() {
		Collection<Actor> actors;
		actors = this.actorService.findAll();
		Assert.notNull(actors);
	}

	@Test
	public void testFindOneActor() {
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
	public void testDeleteActor() {
		Actor actor;
		actor = this.actorService.findOne(super.getEntityId("administrator1"));
		this.actorService.delete(actor);
	}

	@Test
	public void testCheckUserLogin() {

	}

	@Test
	public void testFindByPrincipal() {
		authenticate("admin");
		Actor actor;
		actor = this.actorService.findByPrincipal();
		Assert.notNull(actor);
		unauthenticate();
	}

	@Test
	public void testBrowseAllTrips() {
		Collection<Trip> res;
		res = new ArrayList<Trip>();
		res = this.actorService.browseAllTrips();
		Assert.notNull(res);
	}

	@Test
	public void testSearchTripsBySingleKey() {

		String key;
		key = "Punta";
		Assert.notNull(key);

		Collection<Trip> res;
		res = new ArrayList<Trip>();
		res = this.actorService.searchTripsBySingleKey(key);
		Assert.notNull(res);
	}

	@Test
	public void testSearchTripsByCategory() {
		Category category = new Category();
		category = this.categoryService.findOne(super.getEntityId("category1"));
		Assert.notNull(category);

		Collection<Trip> res;
		res = new ArrayList<Trip>();
		res.addAll(this.actorService.searchTripsByCategory(category));
		Assert.notNull(res);

	}

	@Test
	public void testFindCurriculumRangerByTrip() {

		int i;
		Trip trip;
		trip = this.tripService.findOne(super.getEntityId("trip1"));
		Assert.notNull(trip);
		i = trip.getId();

		Collection<Curriculum> res;
		res = new ArrayList<Curriculum>();
		res.addAll(this.actorService.findCurriculumRangerByTrip(i));
		Assert.notNull(res);

	}

	@Test
	public void testFindAuditByTrip() {
		int i;
		Trip trip;
		trip = this.tripService.findOne(super.getEntityId("trip2"));
		Assert.notNull(trip);
		i = trip.getId();

		Collection<Audit> res;
		res = new ArrayList<Audit>();
		res = this.actorService.findAuditByTrip(i);
		Assert.notNull(res);

	}

	@Test
	public void testBanActor() {
		authenticate("admin");
		Actor actor;
		actor = this.actorService.findOne(super.getEntityId("ranger1"));
		Assert.notNull(actor);

		this.actorService.banActor(actor);
		unauthenticate();
	}

	@Test
	public void testUnBanActor() {
		authenticate("admin");
		Actor actor;
		actor = this.actorService.findOne(super.getEntityId("ranger1"));
		Assert.notNull(actor);

		this.actorService.unbanActor(actor);
		unauthenticate();
	}

	@Test
	public void testSendNotificationBroadcast() {
		authenticate("admin");
		Message message;
		message = this.messageService.findOne(super.getEntityId("message1"));
		Assert.notNull(message);

		this.actorService.SendNotificationBroadcast(message);
		unauthenticate();
	}

	@Test
	public void testCheckSpamWords() {
		authenticate("admin");
		this.actorService.checkSpamWords();
		unauthenticate();
	}

	@Test
	public void testActorToSuspiciousList() {
		authenticate("admin");
		this.actorService.actorToSuspiciousList();
		unauthenticate();
	}

	@Test
	public void testSendMessage() {
		authenticate("ranger01");
		Collection<Actor> actores;
		actores = new ArrayList<Actor>();
		Assert.notNull(actores);

		Actor sender;
		sender = this.actorService.findOne(super.getEntityId("ranger1"));
		Assert.notNull(sender);

		Message message;
		message = this.messageService.findOne(super.getEntityId("message2"));
		Assert.notNull(message);

		this.actorService.sendMessage(actores, sender, message);
		unauthenticate();
	}

	@Test
	public void testDeleteMessage() {
		authenticate("ranger01");
		Actor actor;
		actor = this.actorService.findOne(super.getEntityId("ranger1"));
		Assert.notNull(actor);

		Message message;
		message = this.messageService.findOne(super.getEntityId("message1"));
		Assert.notNull(message);

		this.actorService.deleteMessage(actor, message);
		unauthenticate();
	}

	@Test
	public void testMoveMessage() {
		authenticate("ranger01");
		Message message;
		message = this.messageService.findOne(super.getEntityId("message1"));
		Assert.notNull(message);

		Actor actor;
		actor = this.actorService.findOne(super.getEntityId("ranger1"));
		Assert.notNull(actor);

		Folder folder;
		folder = this.folderService.findOne(super
				.getEntityId("customBoxRanger1"));
		Assert.notNull(folder);

		this.actorService.moveMessage(message, actor, folder);
		unauthenticate();
	}

}
