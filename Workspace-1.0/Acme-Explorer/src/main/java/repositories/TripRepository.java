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
	Object[] avgMinMaxSqtr();
	
	//C-3
	@Query("select avg(t.price), min(t.price), max(t.price), sqrt(sum(t.price*t.price)/count(t.price) - (avg(t.price) * avg(t.price))) from Trip t")
	Object[] avgMinMaxSqtr2();
	
	//C-9
	@Query("select cast(count(t) as float) /(select count(t) from Trip t)  from Trip t where t.reason is not null")
	Double ratioTripsCancelled();
	
	//C-10
	@Query("select t from Trip t group by t having t.application.size > (select avg(t2.application.size)*1.1 from Trip t2) order by t.application.size")
	Collection<Trip> tripsThanAverage();
	
	//C-11
	@Query("select count(t) from Trip t where t.legalText is not null")
	Double tripsLegalTextReferenced();
	
	//B-1
	@Query("select avg(t.note.size), min(t.note.size), max(t.note.size), sqrt(sum(t.note.size*t.note.size)/count(t.note.size)-(avg(t.note.size)*avg(t.note.size))) from Trip t")
	Object[] avgMinMaxSqtr3();
	
	//B-2
	@Query("select avg(t.audit.size), min(t.audit.size), max(t.audit.size), sqrt(sum(t.audit.size*t.audit.size)/count(t.audit.size)-(avg(t.audit.size)*avg(t.audit.size))) from Trip t")
	Object[] avgMinMaxSqtr4();
	
	//B-3
	@Query("select count(t)/((select count(t2) from Trip t2)+0.0) from Trip t where t.audit.size=1")
	Object[] avgMinMaxSqtr5();

}
