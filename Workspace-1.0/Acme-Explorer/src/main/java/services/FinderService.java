package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FinderRepository;
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
		explorerService.checkAuthority();
		
		Collection<Finder> res;
		res = this.finderRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Finder findOne(int finder) {
		explorerService.checkAuthority();
		
		Assert.isTrue(finder != 0);
		Finder res;
		res = this.finderRepository.findOne(finder);
		Assert.notNull(res);
		return res;
	}

	public Finder save(Finder finder) {
		explorerService.checkAuthority();
		
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
	
	// 34.1

	public Collection<Trip> findSearchCriterial(String singleKey, Date start, Date end, Double minPrice, Double maxPrice){
		Collection<Trip> res = new ArrayList<Trip>();
		res.addAll(finderRepository.resultFinder(singleKey, start, end, minPrice, maxPrice));
		
		return res;
	}
}
