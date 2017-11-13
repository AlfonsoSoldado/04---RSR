package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;
import domain.Actor;
import domain.Audit;
import domain.Category;

import domain.Curriculum;

import domain.Trip;

@Service
@Transactional
public class ActorService {

	// Managed repository

	@Autowired
	private ActorRepository actorRepository;

	// Supporting services

	@Autowired
	private UserAccountService userAccountService;
	
	@Autowired
	private TripService tripService;

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

//	public boolean checkAuthority(String authority) {
//		boolean res;
//		Actor actor;
//		Collection<Authority> authorities;
//		res = false;
//
//		try {
//			actor = this.findByPrincipal();
//			authorities = actor.getUserAccount().getAuthorities();
//			for (Authority auth : authorities) {
//				if (auth.getAuthority().equals(authority.toUpperCase())) {
//					res = true;
//					break;
//				}
//			}
//		} catch (IllegalArgumentException e) {
//			res = false;
//		}
//
//		return res;
//	}

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
		
		// Añadimos todos los Curriculum mediante la query.
		
		res.addAll(actorRepository.findCurriculumRangerByTrip(id));
		Assert.notNull(res);
		return res;
	}
	
	//30.2
	public Collection<Audit> findAuditByTrip(int id){
		Collection<Audit> res = new ArrayList<Audit>();
		
		// Añadimos todos los Audit mediante la query
		
		res.addAll(actorRepository.findAuditsByTrip(id));
		Assert.notNull(res);
		return res;

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
	
	
	
//	private boolean checkSpamWords(final Message message) {
//		boolean res;
//
//		res = false;
//		SpamWords bu = null;
//		Collection<String> paspam = bu.getWords();
//		
//		for (String spam : paspam) {
//			res = message.getBody().contains(spam);
//			if (res == true)
//				break;
//		}
//		return res;
//	}
}
