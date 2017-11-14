package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import repositories.ConfigurationRepository;
import repositories.ManagerRepository;
import repositories.MessageRepository;
import repositories.RangerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;
import domain.Actor;
import domain.Audit;
import domain.Category;
import domain.Curriculum;
import domain.Folder;
import domain.ListSuspicious;
import domain.Manager;
import domain.Message;
import domain.Ranger;
import domain.Trip;

@Service
@Transactional
public class ActorService {

	// Managed repository

	@Autowired
	private ActorRepository actorRepository;
	
	@Autowired
	private RangerRepository rangerRepository;
	
	@Autowired
	private ManagerRepository managerRepository;
	
	@Autowired
	private ConfigurationRepository configurationRepository;
	
	@Autowired
	private MessageRepository messageRepository;

	// Supporting services

	@Autowired
	private UserAccountService userAccountService;
	
	@Autowired
	private TripService tripService;
	
	@Autowired
	private AdministratorService administratorService;

	// Constructors

	public ActorService() {
		super();
	}

	// Simple CRUD methods

	public Collection<Actor> findAll() {
		Collection<Actor> res;
		res = this.actorRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Actor findOne(int actorId) {
		Assert.isTrue(actorId != 0);
		Actor res;
		res = this.actorRepository.findOne(actorId);
		Assert.notNull(res);
		return res;
	}

	public Actor save(Actor actor) {
		Assert.notNull(actor);
		Actor res;
		res = this.actorRepository.save(actor);
		return res;
	}

	public void delete(Actor actor) {
		Assert.notNull(actor);
		Assert.isTrue(actor.getId() != 0);
		Assert.isTrue(this.actorRepository.exists(actor.getId()));
		this.actorRepository.delete(actor);
	}

	// Other business methods
	
	public void checkUserLogin() {
		UserAccount userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		Actor actor = this.findByPrincipal();
		Assert.notNull(actor);
	}

	public Actor findByPrincipal() {
		Actor res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		res = this.actorRepository
				.findActorByUserAccountId(userAccount.getId());
		Assert.notNull(res);
		return res;
	}
	
	public boolean checkAuthority() {
		boolean result = false;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		if (userAccount != null)
			result = true;
		return result;
	}

	public UserAccount findByUserAccount(Actor actor) {
		Assert.notNull(actor);
		UserAccount res;
		res = userAccountService.findByActor(actor);
		Assert.notNull(res);
		return res;
	}

	// 10.2

	public Collection<Trip> browseAllTrips() {
		Collection<Trip> res;
		res = this.tripService.findAll();
		return res;
	}

	// 10.3

	public Collection<Trip> searchTripsBySingleKey(String singleKey) {
		Collection<Trip> res;
		res = this.tripService.findTrips(singleKey);
		return res;
	}

	// 10.4
	
	public Collection<Trip> searchTripsByCategory(Category category) {
		Collection<Trip> res;
		res = this.tripService.findTripsByCategory(category);
		return res;
	}

	//30.1
	public Collection<Curriculum> findCurriculumRangerByTrip(int id) {
		Collection<Curriculum> res = new ArrayList<Curriculum>();
		
		// A�adimos todos los Curriculum mediante la query.
		
		res.addAll(actorRepository.findCurriculumRangerByTrip(id));
		Assert.notNull(res);
		return res;
	}
	
	//30.2
	public Collection<Audit> findAuditByTrip(int id){
		Collection<Audit> res = new ArrayList<Audit>();
		
		// A�adimos todos los Audit mediante la query
		
		res.addAll(actorRepository.findAuditsByTrip(id));
		Assert.notNull(res);
		return res;

	}
	
	// 35.2
	public void banActor(Actor actor){
		administratorService.checkAuthority();
		Collection<Actor> res = new ArrayList<Actor>();
		res.addAll(rangerRepository.banRanger());
		res.addAll(managerRepository.banManager());
		if(res.contains(actor)){
			Collection<Authority> authorities = new ArrayList<Authority>();
			authorities.addAll(actor.getUserAccount().getAuthorities());
			for(Authority a: authorities){
				actor.getUserAccount().removeAuthority(a);
			}
		}
	}
	
	// 35.3
	public void unbanActor(Actor actor){
		administratorService.checkAuthority();
		Collection<Actor> res = new ArrayList<Actor>();
		res.addAll(rangerRepository.unbanRanger());
		res.addAll(managerRepository.unbanManager());
		if(res.contains(actor)){
			if(actor.getClass().equals(Ranger.class)){
				Authority auth = new Authority();
				auth.setAuthority("RANGER");
				actor.getUserAccount().addAuthority(auth);
			} else if(actor.getClass().equals(Manager.class)) {
				Authority auth2 = new Authority();
				auth2.setAuthority("MANAGER");
				actor.getUserAccount().addAuthority(auth2);
			}
		}
	}

	// 14.5
	public void SendNotificationBroadcast(Message message) {
		administratorService.checkAuthority();

		Collection<Folder> folders = new ArrayList<Folder>();
		folders = actorRepository.findFoldersByNotification();

		Collection<Message> messages = new ArrayList<Message>();

		for (Folder f : folders) {
			messages.addAll(f.getMessages());
			messages.add(message);

			f.setMessages(messages);

			messages.clear();
		}
	}
	public void checkSpamWords() {
		administratorService.checkAuthority();
		Collection<String> spamWords = new ArrayList<String>();
		spamWords = configurationRepository.findSpamWords();
		
		Collection<Message> messages = new ArrayList<Message>();
		messages = messageRepository.findMessages();
		
		for(String s: spamWords){
			for(Message m: messages){
				if(m.getBody().contains(s) || m.getSubject().contains(s)){
					m.setSpam(true);
				}
			}
		}
	}
	
	public void actorToSuspiciousList(){
		administratorService.checkAuthority();
		
		checkSpamWords();
		
		ListSuspicious ls = new ListSuspicious();
		
		Collection<Actor> actors = new ArrayList<Actor>();
		
		Collection<Message> rangerMessages = new ArrayList<Message>();
		rangerMessages = messageRepository.findRangerMessages();
		
		Collection<Message> managerMessages = new ArrayList<Message>();
		managerMessages = messageRepository.findManagerMessages();
		
		for(Message m: rangerMessages){
			Ranger r;
			r = (Ranger) m.getSender();
			
			if(r.getSuspicious() == false){
				r.setSuspicious(true);
			}
			
			
			actors = ls.getSuspicious();
			actors.add(r);
			
			ls.setSuspicious(actors);
			
			actors.clear();
		}
		
		for(Message m: managerMessages){
			Manager ma;
			ma = (Manager) m.getSender();
			
			if(ma.getSuspicious()==false){
				ma.setSuspicious(true);
			}
			
			actors = ls.getSuspicious();
			actors.add(ma);
			
			ls.setSuspicious(actors);
			
			actors.clear();
		}
	}
}
	
	
//	public void sendMessage(Message message, Actor sender, Actor receiver) {
//
//		Assert.notNull(message);
//		this.checkUserLogin();
//
//		UserAccount userAccount;
//		Message savedMessage, savedMessageCopy;
//		Message messageReceiver;
//		Message messageSender;
//
//		userAccount = LoginService.getPrincipal();
//		Assert.notNull(userAccount);
//		Assert.notNull(sender);
//
//		Message messageCopy = this.messageService.copyMessage(message);
//
//		if (this.checkSpamWords(messageCopy))
//			messageReceiver.setSpam(true);
//			receiver.setFolders(Folders)
//			
//					
//		else
//			messageReceiver = (MessageFolder) receiver.getRe()
//					.toArray()[4];
//
//		messageCopy.setMessageFolder(messageReceiver);
//
//		savedMessage = this.messageService.save(message);
//		savedMessageCopy = this.messageService.save(messageCopy);
//
//		messageSender = message.getMessageFolder();
//
//		messageReceiver.getMessages().add(savedMessageCopy);
//		messageSender.getMessages().add(savedMessage);
//
//		this.messageFolderService.save(messageSender);
//		this.messageFolderService.save(messageReceiver);
//
//	}
	
