package services;

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

@Service
@Transactional
public class ActorService {
	
	// Managed repository
	
	@Autowired
	private ActorRepository actorRepository;
	
	// Supporting services
	
	@Autowired
	private UserAccountService	userAccountService;
	
	// Constructors
	
	public ActorService(){
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
		Assert.isTrue(checkAuthority("ADMIN"));
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
	
	public Actor findByPrincipal() {
		Actor res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		res = this.actorRepository.findActorByUserAccountId(userAccount.getId());
		Assert.notNull(res);
		return res;
	}
	
//	public boolean checkAuthority() {
//		boolean result = false;
//		UserAccount userAccount;
//		userAccount = LoginService.getPrincipal();
//		if (userAccount != null)
//			result = true;
//		return result;
//	}
	
	public boolean checkAuthority(String authority){
		boolean res;
		Actor actor;
		Collection<Authority> authorities;
		res = false;
		
		try {
			actor = this.findByPrincipal();
			authorities = actor.getUserAccount().getAuthorities();
			for(Authority auth: authorities){
				if(auth.getAuthority().equals(authority.toUpperCase())){
					res = true;
					break;
				}
			}
		} catch(IllegalArgumentException e){
			res = false;
		}
		
		return res;
	}
	
	public UserAccount findByUserAccount(Actor actor) {
		Assert.notNull(actor);
		UserAccount res;
		res = userAccountService.findByActor(actor);
		Assert.notNull(res);
		return res;
	}
}
