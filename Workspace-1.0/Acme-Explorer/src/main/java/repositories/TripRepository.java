package repositories;


import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer>{
	
	//12.1 (listing)
	@Query("select t from Trip t where t.manager.id = ?1")
	Collection<Trip> findTripsByManager(int id);
	
	//12.3
//	@Query("select t from Trip where t.publication != null")
//	Collection<Trip> findTripsPublishedAndNotStarted();
	
	//13.1
	@Query("select t from Trip t join t.application a where a.explorer.id = ?1")
	Collection<Trip> findTripsByExplorer(int id);
	
	//13.4
	@Query("select t from Trip t join t.application a where a.status like 'ACCEPTED'")
	Collection<Trip> findTripsAccepted();
}
