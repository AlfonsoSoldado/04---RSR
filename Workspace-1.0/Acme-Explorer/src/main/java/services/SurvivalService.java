package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SurvivalRepository;
import domain.Application;
import domain.Explorer;
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
	private ExplorerService explorerService;

	// Constructors

	public SurvivalService() {
		super();
	}

	// Simple CRUD methods
	
	//43.1
	
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
		this.survivalRepository.delete(survival);
	}

	// Other business methods	
	
	// 43.1
	
	public Collection<Survival> findSurvivalByTrips(){
		managerService.checkAuthority();
		Collection<Survival> res = new ArrayList<Survival>();
		Manager manager;
		manager = managerService.findByPrincipal();
		
		res.addAll(survivalRepository.findSurvivalByManager(manager.getId()));
		Assert.notNull(res);
		return res;
	}
	
	// 43.1
	
	public Survival findOneByTrips(int survival) {
		managerService.checkAuthority();
		Assert.isTrue(survival != 0);
		Survival res = null;
		Collection<Survival> survivals = new ArrayList<Survival>();
		survivals = findSurvivalByTrips();
		for(Survival s: survivals){
			if(s.getId() == survival){
				res = s;
			}
		}
		Assert.notNull(res);
		return res;
	}
	
	// 43.1
	
	public Survival saveByTrips(Survival survival) {
		managerService.checkAuthority();
		Assert.notNull(survival);
		Survival res;
		Collection<Survival> survivals = new ArrayList<Survival>();
		survivals = findSurvivalByTrips();
		Assert.isTrue(survivals.contains(survival));
		res = this.survivalRepository.save(survival);
		return res;
	}
	
	// 43.1
	
	public void deleteByTrips(Survival survival) {
		managerService.checkAuthority();
		Assert.notNull(survival);
		Assert.isTrue(survival.getId() != 0);
		Collection<Survival> survivals = new ArrayList<Survival>();
		survivals = findSurvivalByTrips();
		Assert.isTrue(survivals.contains(survival));
		this.survivalRepository.delete(survival);
	}
	
	// 44.1
	
	public void enrolSurvival(Explorer explorer, Survival survival){
		explorerService.checkAuthority();
		
		Trip trip;
		
		Collection<Survival> survivals = new ArrayList<Survival>();
		Collection<Survival> res = new ArrayList<Survival>();
		Collection<Explorer> explorers = new ArrayList<Explorer>();
		survivals = survivalRepository.findSurvivalByExplorer(explorer.getId());
		
		for(Survival s: survivals){
			if(s.equals(survival)){
				trip = survivalRepository.findTripBySurvival(explorer.getId());
				
				Collection<Application> applications = new ArrayList<Application>();
				applications = survivalRepository.enrolSurvivalExplorer(trip.getId());
				for(Application a: applications){
					if(explorer.getApplication().equals(a) && explorer.getSurvival().contains(survival)){
						res.addAll(explorer.getSurvival());
						res.add(survival);
						
						explorer.setSurvival(res);
						
						res.clear();
						
						explorers.addAll(s.getExplorer());
						explorers.add(explorer);
						
						s.setExplorer(explorers);
						
						explorers.clear();
					}
				}
			}
		}
	}
}
