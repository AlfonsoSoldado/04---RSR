package repositories;


import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer>{
	
	//13.1
	@Query("select t from Trip t join t.application a where a.explorer.id=?1")
	Collection<Trip> findTripsByExplorer(int id);
	
	//13.4
	@Query("select t from Trip t join t.application a where a.status like 'ACCEPTED'")
	Collection<Trip> findTripsAccepted();
}
