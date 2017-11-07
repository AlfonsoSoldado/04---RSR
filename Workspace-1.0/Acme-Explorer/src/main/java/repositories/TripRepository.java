package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer>{
	
	
	
	//C-1
	@Query("select avg(t.application.size), min(t.application.size), max(t.application.size), sqrt(sum(t.application.size*t.application.size)/count(t.application.size)-(avg(t.application.size)*avg(t.application.size))) from Trip t")
	Collection<Integer> avgMinMaxSqtr();
	
	//C-3
	@Query("select avg(t.price), min(t.price), max(t.price), sqrt(sum(t.price*t.price)/count(t.price) - (avg(t.price) * avg(t.price))) from Trip t")
	Collection<Integer> avgMinMaxSqtr2();
	
	
}
