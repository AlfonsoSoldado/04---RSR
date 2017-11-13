package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SurvivalRepository;
import repositories.TripRepository;
import domain.Manager;
import domain.Survival;
import domain.Trip;

@Service
@Transactional
public class SurvivalService {

	// Managed repository

	@Autowired
	private SurvivalRepository survivalRepository;

	// Supporting services
	@Autowired
	private ManagerService managerService;
	
	@Autowired
	private ActorService actorService;

	// Constructors

	public SurvivalService() {
		super();
	}

	// Simple CRUD methods
	
	//43.1: creating
	public Survival create(){
		this.managerService.checkAuthority();
		Manager m = new Manager();
		Trip trip = new Trip();
		Survival survival = new Survival();
		survival.setTrip(trip);
		survival.setManager(m);
		return survival;
	}
	
	

	public Collection<Survival> findAll() {
		this.managerService.checkAuthority();
		Collection<Survival> res;
		res = this.survivalRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Survival findOne(int survival) {
		this.managerService.checkAuthority();
		Assert.isTrue(survival != 0);
		Survival res;
		res = this.survivalRepository.findOne(survival);
		Assert.notNull(res);
		return res;
	}

	public Survival save(Survival survival) {
		this.managerService.checkAuthority();
		Assert.notNull(survival);
		Survival res;
		res = this.survivalRepository.save(survival);
		return res;
	}

	public void delete(Survival survival) {
		this.managerService.checkAuthority();
		Assert.notNull(survival);
		Assert.isTrue(survival.getId() != 0);
		Assert.isTrue(this.survivalRepository.exists(survival.getId()));
		Manager m = survival.getManager();
		Manager a = managerService.findByPrincipal();
		Assert.isTrue(m.equals(a));
		this.survivalRepository.delete(survival);
	}

	// Other business methods	
}
