package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

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
	
}
