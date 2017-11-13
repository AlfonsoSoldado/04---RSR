package repositories;


import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Ranger;

@Repository
public interface RangerRepository extends JpaRepository<Ranger, Integer>{
	
	@Query("select r from Ranger r where r.userAccount.id=?1")
	Ranger findRangerByUserAccountId(int uA);
	
	@Query("select r from Ranger r where r.suspicious is true")
	Collection<Ranger> rangersSuspicious();
	
}
