package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Audit;
import domain.Curriculum;
import domain.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {

	// 12.1 (listing)
	@Query("select t from Trip t where t.manager.id = ?1")
	Collection<Trip> findTripsByManager(int id);

	// 12.3
	// @Query("select t from Trip where t.publication != null")
	// Collection<Trip> findTripsPublishedAndNotStarted();

	// 13.1
	@Query("select t from Trip t join t.application a where a.explorer.id = ?1")
	Collection<Trip> findTripsByExplorer(int id);

	// 13.4
	@Query("select t from Trip t join t.application a where a.status like 'ACCEPTED'")
	Collection<Trip> findTripsAccepted();

	// 10.2
	@Query("select t from Trip t where t.publication!=null and t.cancelled=false")
	Collection<Trip> browseTripsByActor();

	// 10.4 //TODO
//	@Query("select t from Category c where c.Trip t")
//	Collection<Trip> browseTripsByCategories();

	// 10.3
	@Query("select t from Trip t where t.ticker like ?1 or t.title like ?1 or t.description like ?1")
	Collection<Trip> findTrips(String search);
	
	//30.1
	@Query("select r.curriculum from Trip t join t.ranger r where t.id = ?1")
	Collection<Curriculum> findCurriculumRangerByTrip(int id);
	
	// 30.2
	@Query("select t.audit from Trip t where t.id = ?1")
	Collection<Audit> findAuditsByTrip(int id);
}
