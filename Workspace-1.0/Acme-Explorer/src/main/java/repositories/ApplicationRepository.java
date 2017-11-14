package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;
import domain.Trip;

@Repository
public interface ApplicationRepository extends
		JpaRepository<Application, Integer> {

	// 12.3
	@Query("select a from Manager m join m.application a where m.id = ?1")
	Collection<Application> findApplicationsByManager(int id);

	// 13.2
	@Query("select a from Explorer e join e.application a where e.id = ?1 group by a.status")
	Collection<Application> findApplicationByExplorer(int id);

	// 12.2
	@Query("select a from Manager m join m.application a where m.id = ?1")
	Collection<Application> findListApplication(int id);

	// 12.2
	@Query("select a from Manager m join m.application a where m.id = ?1 and a.status = 'PENDING'")
	Collection<Application> findListApplicationPending(int id);

	// 13.3
	@Query("select a from Application a where a.status = 'DUE'")
	Collection<Application> findListApplicationDue();

	// 13.4
//	@Query("select a from Explorer e join e.application a where e.id = ?1 and a.status = 'ACCEPTED' and select from Trip t t.publication < CURRENT_DATE")
//	Application applicationExplorer(int explorer, int application);
	
	@Query("select t from Explorer e join e.application a join a.trip t where e.id = ?1 and a.status = 'ACCEPTED' and t.publication < CURRENT_DATE")
	Trip applicationExplorer(int explorer);

}
