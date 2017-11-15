package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TripRepository;
import domain.Application;
import domain.Audit;
import domain.Category;
import domain.LegalText;
import domain.Manager;
import domain.Note;
import domain.Ranger;
import domain.Stage;
import domain.Story;
import domain.Survival;
import domain.Trip;
import domain.Value;

@Service
@Transactional
public class TripService {

	// Managed repository

	@Autowired
	private TripRepository tripRepository;

	// Supporting services

	@Autowired
	private ManagerService managerService;

	@Autowired
	private ExplorerService explorerService;

	// Constructors
	public TripService() {
		super();
	}

	// Simple CRUD methods

	// 12.1

	public Trip create() {
		Manager m = new Manager();
		Collection<Audit> audits = new ArrayList<Audit>();
		Collection<Note> notes = new ArrayList<Note>();
		Collection<Application> applications = new ArrayList<Application>();
		Collection<Story> stories = new ArrayList<Story>();
		Collection<Stage> stages = new ArrayList<Stage>();
		LegalText legalText = new LegalText();
		Category category = new Category();
		Ranger ranger = new Ranger();
		Collection<Survival> survivals = new ArrayList<Survival>();
		Collection<Value> value = new ArrayList<Value>();
		Trip trip = new Trip();

		trip.setManager(m);
		trip.setAudit(audits);
		trip.setNote(notes);
		trip.setApplication(applications);
		trip.setStory(stories);
		trip.setStage(stages);
		trip.setLegalText(legalText);
		trip.setCategory(category);
		trip.setRanger(ranger);
		trip.setSurvival(survivals);
		trip.setValue(value);
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
		return res;
	}

	// 12.1

	public void delete(Trip trip) {
		Assert.notNull(trip);
		Assert.isTrue(trip.getId() != 0);
		Assert.isTrue(trip.getPublication().after(new Date())
				|| trip.getPublication() == null);
		Assert.notNull(trip);
		Manager m = trip.getManager();
		Manager a = managerService.findByPrincipal();
		Assert.isTrue(m.equals(a));
		this.tripRepository.delete(trip);
	}

	// Other business methods

	// 12.1

	public Collection<Trip> findTripsByManager(int id) {
		managerService.checkAuthority();

		Collection<Trip> res = new ArrayList<Trip>();
		res = tripRepository.findTripsByManager(id);
		Assert.notNull(res);
		return res;
	}

	// 12.1

	public Trip editByManager(int id) {
		managerService.checkAuthority();
		Trip res;
		Trip t;
		t = tripRepository.findOne(id);
		Assert.isTrue(t.getPublication().after(new Date())
				|| t.getPublication() == null);
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
		res.addAll(tripRepository.findTripsByExplorer(id));
		Assert.notNull(res);
		return res;
	}

	// 10.2

	public Collection<Trip> browseTripsByActor() {
		Collection<Trip> res = new ArrayList<Trip>();
		res = this.tripRepository.browseTripsByActor();
		Assert.notNull(res);
		return res;
	}

	// 10.3

	public Collection<Trip> findTrips(String search) {
		Assert.notNull(search);
		Assert.isTrue(search.length() != 0);
		return this.tripRepository.findTrips(search);
	}

	// 10.4

	public Collection<Trip> findTripsByCategory(Category category) {
		Collection<Trip> res;
		res = new ArrayList<Trip>();
		Assert.notNull(category);
		Assert.isTrue(category.getId() != 0);
		res.addAll(tripRepository.browseTripsByCategories(category.getId()));

		return res;
	}

	// 12.3

	public void cancelTrip(Trip trip) {
		managerService.checkAuthority();
		Collection<Trip> trips = new ArrayList<Trip>();
		trips = tripRepository.cancelTrip();
		for (Trip t : trips) {
			if (t.equals(trip)) {
				t.setCancelled(true);
			}
		}
	}

	// 13.4

	public void tripApplicationExplorer(Trip trip) {
		explorerService.checkAuthority();
		Assert.notNull(trip);
		Collection<Trip> trips = new ArrayList<Trip>();
		trips = tripRepository.findTripsAccepted();

		for (Trip t : trips) {
			if (t.equals(trip)) {
				t.setCancelled(true);
			}
		}
	}
}
