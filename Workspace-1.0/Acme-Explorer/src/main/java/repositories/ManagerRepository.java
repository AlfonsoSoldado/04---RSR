package repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer>{
	
	@Query("select m from Manager m.userAccount.id=?1")
	Manager findManagerByUserAccountId(int uA);
	
	//C-2
	@Query("select avg(m.trip.size), min(m.trip.size), max(m.trip.size), sqrt(sum(m.trip.size*m.trip.size)/count(m.trip.size)-(avg(m.trip.size)*avg(m.trip.size))) from Manager m")
	Object[] avgMinMaxSqtr();
	
	//B-6
	@Query("select count(m)/((select count(m2) from Manager m2)+0.0) from Manager m where m.suspicious=true")
	Double ratioManagerSuspicious();
}
