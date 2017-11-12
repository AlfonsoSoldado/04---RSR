package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FinderRepository;
import security.Authority;
import domain.Explorer;
import domain.Finder;
import domain.Trip;

@Service
@Transactional
public class FinderService {
	
	// Managed repository

	@Autowired
	private FinderRepository finderRepository;
	
	// Supporting services
	
	@Autowired
	private ExplorerService explorerService;
	@Autowired
	private TripService tripService;
	@Autowired
	private ActorService actorService;
	
	// Constructors
	
	public FinderService(){
		super();
	}
	
	// Simple CRUD methods
	
	public Finder create() {
		Finder res;
		res = new Finder();
		return res;
	}
	
	public Collection<Finder> findAll() {
		Collection<Finder> res;
		res = this.finderRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Finder findOne(int finder) {
		Assert.isTrue(finder != 0);
		Finder res;
		res = this.finderRepository.findOne(finder);
		Assert.notNull(res);
		return res;
	}

	public Finder save(Finder finder) {
		Explorer explorer;
		explorer = this.explorerService.findByPrincipal();
		
		Assert.notNull(finder);
		
		Finder res;
		res = this.finderRepository.save(finder);
		
		Collection<Finder> finderCollection = new ArrayList<Finder>();
		finderCollection.add(res);
		
		explorer.setFinder(finderCollection);
		
		return res;
	}

	// Other business methods
	
	public Finder editByExplorer(int id){
		Finder res;
		Finder f;
		f = finderRepository.findOne(id);
		Assert.notNull(f);
		Assert.isTrue(actorService.findByPrincipal().getUserAccount()
				.getAuthorities().contains(Authority.EXPLORER));
		res = finderRepository.save(f);
		return res;
	}

	public Collection<Finder> findSearchCriterial(){
		Collection<Finder> res = new ArrayList<Finder>();
		
		Collection<Finder> totalFinder = this.finderRepository.findAll();
		Collection<Trip> totalTrip = this.tripService.findAll();
		
		for(Finder f: totalFinder){
			for(Trip t: totalTrip){
				if(f.getSingleKey().equals(t.getTitle())){
					res.add(f);
					f.getResult().add(t.getTitle());
				}
			}
		}	
		return res;
	}
}
