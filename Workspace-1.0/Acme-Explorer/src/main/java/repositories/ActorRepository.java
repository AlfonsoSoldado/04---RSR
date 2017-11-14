package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;
import domain.Audit;
import domain.Curriculum;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

	@Query("select a from Actor a where a.userAccount.id=?1")
	Actor findActorByUserAccountId(int uA);

	//30.1
	@Query("select r.curriculum from Trip t join t.ranger r where t.id = ?1")
	Collection<Curriculum> findCurriculumRangerByTrip(int id);
	
	//30.2
	@Query("select t.audit from Trip t where t.id = ?1")
	Collection<Audit> findAuditsByTrip(int id);
	
}
