package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TripRepository;
import domain.Trip;

@Service
@Transactional
public class TripService {

	// Managed repository

	@Autowired
	private TripRepository tripRepository;

	// Supporting services

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

}
