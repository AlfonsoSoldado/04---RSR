package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {

	// 12.1 (listing)
	@Query("select t from Trip t where t.manager.id = ?1")
	Collection<Trip> findTripsByManager(int id);

	// 13.1
	@Query("select t from Trip t join t.application a where a.explorer.id = ?1")
	Collection<Trip> findTripsByExplorer(int id);

	// 13.4
	@Query("select t from Trip t join t.application a where a.status = 'ACCEPTED' and t.tripStart > CURRENT_DATE")
	Collection<Trip> findTripsAccepted();

	// 10.2
	@Query("select t from Trip t where t.publication!=null and t.cancelled=false")
	Collection<Trip> browseTripsByActor();

	// 10.4 
	@Query("select t from Trip t join t.category c where c.id = ?1")
	Collection<Trip> browseTripsByCategories(int id);

	// 10.3
	@Query("select t from Trip t where t.ticker like ?1 or t.title like ?1 or t.description like ?1")
	Collection<Trip> findTrips(String search);
	
	// 12.3
	@Query("select t from Trip t where t.publication < CURRENT_DATE and t.tripStart < CURRENT_DATE and t.cancelled = false")
	Collection<Trip> cancelTrip();
}
