package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import domain.Message;

@Service
@Transactional
public class MessageService {

	// Managed repository

	@Autowired
	private MessageRepository messageRepository;

	// Supporting services

	// Constructors

	public MessageService() {
		super();
	}

	// Simple CRUD methods

	public Collection<Message> findAll() {
		Collection<Message> res;
		res = this.messageRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Message findOne(int message) {
		Assert.isTrue(message != 0);
		Message res;
		res = this.messageRepository.findOne(message);
		Assert.notNull(res);
		return res;
	}

	public Message save(Message message) {
		Assert.notNull(message);
		Message res;
		res = this.messageRepository.save(message);
		return res;
	}

	public void delete(Message message) {
		Assert.notNull(message);
		Assert.isTrue(message.getId() != 0);
		Assert.isTrue(this.messageRepository.exists(message.getId()));
		this.messageRepository.delete(message);
	}

	// Other business methods

}
