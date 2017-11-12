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

	// Constructors

	public SurvivalService() {
		super();
	}

	// Simple CRUD methods
	
	//43.1: creating
	public Survival create(){
		Manager m = new Manager();
		Trip trip = new Trip();
		Survival survival = new Survival();
		survival.setTrip(trip);
		survival.setManager(m);
		return survival;
	}
	
	

	public Collection<Survival> findAll() {
		Collection<Survival> res;
		res = this.survivalRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Survival findOne(int survival) {
		Assert.isTrue(survival != 0);
		Survival res;
		res = this.survivalRepository.findOne(survival);
		Assert.notNull(res);
		return res;
	}

	public Survival save(Survival survival) {
		Assert.notNull(survival);
		Survival res;
		res = this.survivalRepository.save(survival);
		return res;
	}

	public void delete(Survival survival) {
		Assert.notNull(survival);
		Assert.isTrue(survival.getId() != 0);
		Assert.isTrue(this.survivalRepository.exists(survival.getId()));
		Manager m = survival.getManager();
		Manager a = managerService.findByPrincipal();
		Assert.isTrue(m.equals(a));
		this.survivalRepository.delete(survival);
	}

	// Other business methods
	
	//43.1: listing
//	public Collection<Survival> findSurvivalByManager(int id){
//		Collection<Survival> res = new ArrayList<Survival>();
//		Manager m = new Manager();
//		m = managerService.findByPrincipal();
//		Assert.notNull(m);
//		res.addAll(survivalRepository.findSurvivalByManager(id));
//		Assert.notNull(res);
//		return res;
//	}
	
	
	
	//43.1: modifying
	public Survival editByManager(int id){
		Survival res;
		Survival s;
		s = survivalRepository.findOne(id);
		Assert.notNull(s);
		Manager m = s.getManager();
		Manager a = managerService.findByPrincipal();
		Assert.isTrue(m.equals(a));
		
		res = survivalRepository.save(s);
		return res;
	}
	
	
}
