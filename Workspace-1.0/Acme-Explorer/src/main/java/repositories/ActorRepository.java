package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;
import domain.Audit;
import domain.Curriculum;
import domain.Folder;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

	@Query("select a from Actor a where a.userAccount.id=?1")
	Actor findActorByUserAccountId(int uA);

	// 14.5
	@Query("select f from Actor a join a.folders f where f.name = 'Notification'")
	Collection<Folder> findFoldersByNotification();

	// 30.1
	@Query("select r.curriculum from Trip t join t.ranger r where t.id = ?1")
	Collection<Curriculum> findCurriculumRangerByTrip(int id);

	// 30.2
	@Query("select t.audit from Trip t where t.id = ?1")
	Collection<Audit> findAuditsByTrip(int id);

	// 11.4
	@Query("select f from Actor a join a.folders f where f.systemFolder = true and a.id = ?1")
	Collection<Folder> findSystemFolders(int id);
}
