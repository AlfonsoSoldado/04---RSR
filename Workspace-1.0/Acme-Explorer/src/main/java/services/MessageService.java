package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.MessageRepository;

@Service
@Transactional
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;
	
	public MessageService(){
		super();
	}
}
