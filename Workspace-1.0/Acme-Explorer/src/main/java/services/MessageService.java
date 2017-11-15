package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import domain.Actor;
import domain.Folder;
import domain.Message;

@Service
@Transactional
public class MessageService {

	// Managed repository

	@Autowired
	private MessageRepository messageRepository;

	// Supporting services
	@Autowired
	private ActorService actorService;

	// Constructors

	public MessageService() {
		super();
	}

	// Simple CRUD methods

	public Message create() {
		actorService.checkAuthority();

		Message message;
		message = new Message();
		Actor sender = this.actorService.findByPrincipal();
		Date moment;
		Collection<Actor> recipient;
		Collection<Folder> folder;
		folder = new ArrayList<Folder>();
		recipient = new ArrayList<Actor>();
		moment = new Date(System.currentTimeMillis() - 1);
		message.setSender(sender);
		message.setMoment(moment);
		message.setRecipient(recipient);
		message.setSpam(false);
		message.setFolder(folder);
		return message;
	}

	public Collection<Message> findAll() {
		actorService.checkAuthority();

		Collection<Message> res;
		res = this.messageRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Message findOne(int message) {
		actorService.checkAuthority();

		Assert.isTrue(message != 0);
		Message res;
		res = this.messageRepository.findOne(message);
		Assert.notNull(res);
		return res;
	}

	public Message save(Message message) {
		actorService.checkAuthority();

		Assert.notNull(message);
		Message res;
		res = this.messageRepository.save(message);
		return res;
	}

	public void delete(Message message) {
		actorService.checkAuthority();

		Assert.notNull(message);
		Assert.isTrue(message.getId() != 0);
		Assert.isTrue(this.messageRepository.exists(message.getId()));
		this.messageRepository.delete(message);
	}

	// Other business methods

}
