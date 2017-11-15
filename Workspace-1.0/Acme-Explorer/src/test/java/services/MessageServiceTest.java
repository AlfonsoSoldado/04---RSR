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
import domain.Actor;
import domain.Folder;
import domain.Message;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class MessageServiceTest extends AbstractTest {

	// Service under test ----------------

	@Autowired
	private MessageService messageService;

	// Supporting services ---------------

	@Autowired
	private ActorService actorService;
	@Autowired
	private FolderService folderService;

	// Test ------------------------------

	@Test
	public void testCreateMessage() {
		authenticate("ranger01");
		Message message;
		message = this.messageService.create();
		Assert.notNull(message);
		unauthenticate();
	}

	@Test
	public void testFindAllMessage() {
		authenticate("ranger01");
		Collection<Message> messages;
		messages = new ArrayList<Message>();
		messages = this.messageService.findAll();
		Assert.notNull(messages);
		unauthenticate();
	}

	@Test
	public void testFindOneMessage() {
		authenticate("ranger01");
		Message message;
		message = this.messageService.findOne(super.getEntityId("message1"));
		Assert.notNull(message);
		unauthenticate();
	}

	@Test
	public void testSaveMessage() {
		authenticate("ranger01");
		Message message;
		message = this.messageService.create();

		Date moment = new Date(System.currentTimeMillis() - 1);
		message.setMoment(moment);

		message.setSubject("Subject");

		message.setBody("Body");

		message.setPriority("LOW");

		message.setSpam(false);

		Actor sender;
		sender = this.actorService.findOne(super.getEntityId("ranger1"));
		message.setSender(sender);

		Collection<Actor> recipient;
		recipient = new ArrayList<Actor>();
		Actor rec1;
		rec1 = this.actorService.findOne(super.getEntityId("explorer1"));
		recipient.add(rec1);
		message.setRecipient(recipient);

		Collection<Folder> folders;
		folders = new ArrayList<Folder>();
		Folder folder;
		folder = this.folderService.create();
		folders.add(folder);
		message.setFolder(folders);

		this.messageService.save(message);
		unauthenticate();
	}

	@Test
	public void testDeleteMessage() {
		authenticate("ranger01");
		Message message;
		message = this.messageService.findOne(super.getEntityId("message2"));
		this.messageService.delete(message);
		unauthenticate();
	}
}
