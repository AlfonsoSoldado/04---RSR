package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrator;
import domain.Trip;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer>{
	
	@Query("select a from Administrator a where a.userAccount.id=?1")
	Administrator findAdministratorByUserAccountId(int uA);
	
	// C-5
	@Query("select cast(count(a) as float)/(select count(a) from Application a) from Application a where a.status='PENDING'")
	Double applicationPending();

	// C-6
	@Query("select cast(count(a) as float)/(select count(a) from Application a) from Application a where a.status='DUE'")
	Double applicationDue();

	// C-7
	@Query("select cast(count(a) as float)/(select count(a) from Application a) from Application a where a.status='ACCEPTED'")
	Double applicationAccepted();

	// C-8
	@Query("select cast(count(a) as float)/(select count(a) from Application a) from Application a where a.status='CANCELLED'")
	Double applicationCancelled();

	// B-5
	@Query("select count(c)/((select count(c2) from Curriculum c2)+0.0) from Curriculum c where c.endorserRecord.size>0")
	Double ratioRangerEndorser();
	
	//C-2
	@Query("select avg(m.trip.size), min(m.trip.size), max(m.trip.size), sqrt(sum(m.trip.size*m.trip.size)/count(m.trip.size)-(avg(m.trip.size)*avg(m.trip.size))) from Manager m")
	Object[] avgMinMaxSqtrManager();

	// B-6
	@Query("select count(m)/((select count(m2) from Manager m2)+0.0) from Manager m where m.suspicious=true")
	Double ratioManagerSuspicious();
	
	//C-4
	@Query("select avg(r.trip.size),min(r.trip.size),max(r.trip.size), sqrt(sum(r.trip.size*r.trip.size)/count(r.trip.size)-(avg(r.trip.size)*avg(r.trip.size))) from Ranger r")
	Object[] avgMinMaxSqtrRanger();

	// B-4
	@Query("select cast(count(r) as float)/(select count(rt) from Ranger rt) from Ranger r where r.curriculum is not null")
	Double ratioRangerCurriculum();

	// B-7
	@Query("select cast(count(r) as float)/(select count(r) from Ranger r) from Ranger r where r.suspicious=true")
	Double ratioSuspiciousRanger();
	
	// C-1
	@Query("select avg(t.application.size), min(t.application.size), max(t.application.size), sqrt(sum(t.application.size*t.application.size)/count(t.application.size)-(avg(t.application.size)*avg(t.application.size))) from Trip t")
	Object[] avgMinMaxSqtr();

	// C-3
	@Query("select avg(t.price), min(t.price), max(t.price), sqrt(sum(t.price*t.price)/count(t.price) - (avg(t.price) * avg(t.price))) from Trip t")
	Object[] avgMinMaxSqtr2();

	// C-9
	@Query("select cast(count(t) as float) /(select count(t) from Trip t)  from Trip t where t.reason is not null")
	Double ratioTripsCancelled();

	// C-10
	@Query("select t from Trip t group by t having t.application.size > (select avg(t2.application.size)*1.1 from Trip t2) order by t.application.size")
	Collection<Trip> tripsThanAverage();

	// C-11
	@Query("select count(t) from Trip t where t.legalText is not null")
	Double tripsLegalTextReferenced();

	// B-1
	@Query("select avg(t.note.size), min(t.note.size), max(t.note.size), sqrt(sum(t.note.size*t.note.size)/count(t.note.size)-(avg(t.note.size)*avg(t.note.size))) from Trip t")
	Object[] avgMinMaxSqtr3();

	// B-2
	@Query("select avg(t.audit.size), min(t.audit.size), max(t.audit.size), sqrt(sum(t.audit.size*t.audit.size)/count(t.audit.size)-(avg(t.audit.size)*avg(t.audit.size))) from Trip t")
	Object[] avgMinMaxSqtr4();

	// B-3
	@Query("select count(t)/((select count(t2) from Trip t2)+0.0) from Trip t where t.audit.size=1")
	Object[] avgMinMaxSqtr5();
}
