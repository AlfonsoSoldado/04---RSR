package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Ranger;

@Repository
public interface RangerRepository extends JpaRepository<Ranger, Integer>{
	
	@Query("select r from Ranger r.userAccount.id=?1")
	Ranger findRangerByUserAccountId(int uA);
	
	//C-4
	@Query("select avg(r.trip.size),min(r.trip.size),max(r.trip.size), sqrt(sum(r.trip.size*r.trip.size)/count(r.trip.size)-(avg(r.trip.size)*avg(r.trip.size))) from Ranger r")
	Object[] avgMinMaxSqtr();
	
	//B-4
	@Query("select cast(count(r) as float)/(select count(rt) from Ranger rt) from Ranger r where r.curriculum is not null")
	Double ratioRangerCurriculum();
	
	//B-7
	@Query("select cast(count(r) as float)/(select count(r) from Ranger r) from Ranger r where r.suspicious=true")
	Double ratioSuspiciousRanger();
	
}
