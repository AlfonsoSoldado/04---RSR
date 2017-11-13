package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TripRepository;
import security.Authority;
import domain.Application;
import domain.Audit;
import domain.Category;
import domain.Curriculum;
import domain.LegalText;
import domain.Manager;
import domain.Note;
import domain.Ranger;
import domain.Stage;
import domain.Story;
import domain.Survival;
import domain.Trip;

@Service
@Transactional
public class TripService {

	// Managed repository
	@Autowired
	private TripRepository tripRepository;

	// Supporting services
	// 12
	@Autowired
	private ManagerService managerService;

	// 13
	@Autowired
	private ActorService actorService;

	// Constructors
	public TripService() {
		super();
	}

	// Simple CRUD methods

	// 12.1 (creating)
	public Trip create() {
		//TODO: mirar lo del manager
		Manager m = new Manager();
		m = managerService.findByPrincipal();
		Assert.notNull(m);
		
		Collection<Audit> audits = new ArrayList<Audit>();
		Collection<Note> notes = new ArrayList<Note>();
		Application application = new Application();
		Collection<Story> stories = new ArrayList<Story>();
		Collection<Stage> stages = new ArrayList<Stage>();
		LegalText legalText = new LegalText();
		Category category = new Category();
		Ranger ranger = new Ranger();
		Collection<Survival> survivals = new ArrayList<Survival>();
		// TODO: meter value??
		Trip trip = new Trip();
		m.getTrip().add(trip);
		trip.setManager(m);
		trip.setAudit(audits);
		trip.setNote(notes);
		trip.setApplication(application);
		trip.setStory(stories);
		trip.setStage(stages);
		trip.setLegalText(legalText);
		trip.setCategory(category);
		trip.setRanger(ranger);
		trip.setSurvival(survivals);
		return trip;
	}

	public Collection<Trip> findAll() {
		Collection<Trip> res = new ArrayList<Trip>();
		res = this.tripRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Trip findOne(int trip) {	
		Assert.isTrue(trip != 0);
		Trip res;
		res = this.tripRepository.findOne(trip);
		Assert.notNull(res);
		return res;
	}

	public Trip save(Trip trip) {
		Assert.notNull(trip);
		Trip res;
		res = this.tripRepository.save(trip);
		if(res.getPublication().before(res.getTripStart())){
			res.setCancelled(true);
		}
		Collection<Trip> trips = new ArrayList<Trip>();
		Date date = new Date(System.currentTimeMillis()-1);
		Assert.isTrue(actorService.findByPrincipal().getUserAccount()
				.getAuthorities().contains(Authority.EXPLORER));
		trips.addAll(tripRepository.findTripsAccepted());
		for(Trip t: trips){
			if(t.getTripStart().after(date) && t.getApplication().getStatus().equals("ACCEPTED")){
				t.setCancelled(true);
			}
		}
		
		return res;
	}

	// 12.1 (deleting)
	public void delete(Trip trip) {
		Assert.notNull(trip);
		Assert.isTrue(trip.getId() != 0);
		// as they have not been published
		Assert.isTrue(trip.getPublication().after(new Date()) || trip.getPublication() == null);
		// comprobamos que el trip seleccionado sea de este manager
		Assert.notNull(trip);
		Manager m = trip.getManager();
		Manager a = managerService.findByPrincipal();
		Assert.isTrue(m.equals(a));
		this.tripRepository.delete(trip);
	}

	// Other business methods

	// 12.1 (listing)
	public Collection<Trip> findTripsByManager(int id) {
		Assert.isTrue(actorService.checkAuthority("MANAGER"));
		
		Collection<Trip> res = new ArrayList<Trip>();
		res = tripRepository.findTripsByManager(id);
		Assert.notNull(res);
		return res;
	}

	// 12.1 (modifying)
	public Trip editByManager(int id) {
		Assert.isTrue(actorService.checkAuthority("MANAGER"));
		
		Trip res;
		Trip t;
		// selecciono el trip que quiero editar
		t = tripRepository.findOne(id);
		// as long as they have not been published
		Assert.isTrue(t.getPublication().after(new Date()) || t.getPublication() == null);
		// comprobamos que el trip seleccionado sea de este manager
		Assert.notNull(t);
		Manager m = t.getManager();
		Manager a = managerService.findByPrincipal();
		Assert.isTrue(m.equals(a));

		res = tripRepository.save(t);
		return res;
	}

	// 13.1
	public Collection<Trip> findTripsByExplorer(int id) {
		Collection<Trip> res = new ArrayList<Trip>();
		// a�adimos todos los trips mediante la query
		res.addAll(tripRepository.findTripsByExplorer(id));
		Assert.notNull(res);
		return res;
	}

	// 13.4
//	public Collection<Trip> findTripsAccepted() {
//		Collection<Trip> res = new ArrayList<Trip>();
//		Collection<Trip> trips = new ArrayList<Trip>();
//		Date date = new Date(System.currentTimeMillis()-1);
//		// comprobamos que es un Explorer
//		Assert.isTrue(actorService.findByPrincipal().getUserAccount()
//				.getAuthorities().contains(Authority.EXPLORER));
//		// a�adimos los trips
//		trips.addAll(tripRepository.findTripsAccepted());
//		Assert.notNull(res);
//		for (Trip t : trips) {
//			if (t.getTripStart().after(date) == true) {
//				res.add(t);
//			}
//		}
//		return res;
//	}
	
	public void cancelAcceptedTrips(){
		Collection<Trip> trips = new ArrayList<Trip>();
		Date date = new Date(System.currentTimeMillis()-1);
		Assert.isTrue(actorService.findByPrincipal().getUserAccount()
				.getAuthorities().contains(Authority.EXPLORER));
		trips.addAll(tripRepository.findTripsAccepted());
		for(Trip t: trips){
			if(t.getTripStart().after(date)){
				t.setCancelled(true);
			}
		}
	}

	// 10.2
	public Collection<Trip> browseTripsByActor() {
		Collection<Trip> res = new ArrayList<Trip>();
		res = this.tripRepository.browseTripsByActor();
		Assert.notNull(res);
		return res;
	}

	// 10.4
//	public Collection<Trip> browseTripsByCategories() {
//		Collection<Trip> res = new ArrayList<Trip>();
//		res = this.tripRepository.browseTripsByCategories();
//		Assert.notNull(res);
//		return res;
//	}

	// 10.3
	public Collection<Trip> findTrips(String search) {

		Assert.notNull(search);
		Assert.isTrue(search.length() != 0);

		return this.tripRepository.findTrips(search);
	}
	
	// 10.4
	public Collection<Trip> findTripsByCategory(Category category){
		Collection<Trip> res;
		Assert.notNull(category);
		Assert.isTrue(category.getId() != 0);
		res = this.tripRepository.browseTripsByCategories(category.getId());
		
		return res;
	}
	
	//30.1
	public Collection<Curriculum> findCurriculumRangerByTrip(int id) {
		Collection<Curriculum> res = new ArrayList<Curriculum>();
		// a�adimos todos los Curriculum mediante la query
		res.addAll(tripRepository.findCurriculumRangerByTrip(id));
		Assert.notNull(res);
		return res;
	}
	
	//30.2
	public Collection<Audit> findAuditByTrip(int id){
		Collection<Audit> res = new ArrayList<Audit>();
		// a�adimos todos los Audit mediante la query
		res.addAll(tripRepository.findAuditsByTrip(id));
		Assert.notNull(res);
		return res;
	}

}
