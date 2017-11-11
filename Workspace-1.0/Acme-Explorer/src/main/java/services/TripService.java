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
import domain.Trip;

@Service
@Transactional
public class TripService {

	// Managed repository

	@Autowired
	private TripRepository tripRepository;

	// Supporting services

	// 13
	@Autowired
	private ActorService actorService;

	// Constructors

	public TripService() {
		super();
	}

	// Simple CRUD methods

	public Collection<Trip> findAll() {
		Collection<Trip> res;
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

	public void delete(Trip trip) {
		Assert.notNull(trip);
		Assert.isTrue(trip.getId() != 0);
		Assert.isTrue(this.tripRepository.exists(trip.getId()));
		this.tripRepository.delete(trip);
	}

	// Other business methods

	// 13.1
	public Collection<Trip> findTripsByExplorer(int id) {
		Collection<Trip> res = new ArrayList<Trip>();
		res.addAll(tripRepository.findTripsByExplorer(id));
		Assert.notNull(res);
		return res;
	}

	// 13.4
	public Collection<Trip> findTripsAccepted() {
		Collection<Trip> res = new ArrayList<Trip>();
		Collection<Trip> trips = new ArrayList<Trip>();
		Date date = new Date();
		// comprobamos que es un Explorer
		Assert.isTrue(actorService.findByPrincipal().getUserAccount()
				.getAuthorities().contains(Authority.EXPLORER));
		// añadimos los trips
		trips.addAll(tripRepository.findTripsAccepted());
		Assert.notNull(res);
		for (Trip t : trips) {
			if (t.getTripStart().after(date) == true) {
				res.add(t);
			}
		}
		return res;
	}

}
