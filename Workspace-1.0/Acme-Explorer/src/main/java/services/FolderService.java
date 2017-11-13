package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FolderRepository;
import domain.Actor;
import domain.Folder;
import domain.Message;

@Service
@Transactional
public class FolderService {

	// Managed repository

	@Autowired
	private FolderRepository folderRepository;

	// Supporting services

	@Autowired
	private ActorService actorService;

	// Constructors

	public FolderService() {
		super();
	}

	// Simple CRUD methods

	public Folder create() {
		Folder folder = new Folder();
		Collection<Message> message = new ArrayList<Message>();
		folder.setMessages(message);
		return folder;
	}

	public Collection<Folder> findAll() {
		Collection<Folder> res;
		res = this.folderRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Folder findOne(int folder) {
		Assert.isTrue(folder != 0);
		Folder res;
		res = this.folderRepository.findOne(folder);
		Assert.notNull(res);
		return res;
	}

	public Folder save(Folder folder) {
		Actor actor = this.actorService.findByPrincipal();
		Assert.notNull(actor);
		Assert.notNull(folder);
		Folder res;
		res = this.folderRepository.save(folder);
		actor.getFolders().add(res);
		Assert.notNull(res);
		return res;
	}

	public void delete(Folder folder) {
		Assert.isTrue(folder.getSystemFolder() == false);
		Assert.isTrue(folder.getId() != 0);
		Assert.isTrue(this.folderRepository.exists(folder.getId()));
		this.folderRepository.delete(folder);
	}

	// Other business methods

	public Collection<Folder> systemFolders() {
		Collection<Folder> folders = new ArrayList<Folder>();
		
		Folder inBox = new Folder();
		Folder outBox = new Folder();
		Folder notification = new Folder();
		Folder trash = new Folder();
		Folder spam = new Folder();
		
		inBox.setName("In Box");
		outBox.setName("Out Box");
		notification.setName("Notification");
		trash.setName("Trash");
		spam.setName("Spam");
		
		inBox.setSystemFolder(true);
		outBox.setSystemFolder(true);
		notification.setSystemFolder(true);
		trash.setSystemFolder(true);
		spam.setSystemFolder(true);
		
		folders.add(inBox);
		folders.add(outBox);
		folders.add(notification);
		folders.add(trash);
		folders.add(inBox);
		
		return folders;
	}
}
