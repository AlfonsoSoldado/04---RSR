package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;
import domain.Survival;
import domain.Trip;

@Repository
public interface SurvivalRepository extends JpaRepository<Survival, Integer> {

	// 44.1
	@Query("select s.trip from Survival s join s.explorer e where e.id = ?1")
	Trip findTripBySurvival(int explorer);

	// 44.1
	@Query("select a from Trip t join t.application a where t.id = ?1 and a.status = 'ACCEPTED'")
	Collection<Application> enrolSurvivalExplorer(int trip);

	// 43.1
	@Query("select s from Survival s join s.explorer m where m.id = ?1")
	Collection<Survival> findSurvivalByExplorer(int explorer);

	// 43.1
	@Query("select s from Survival s join s.manager m where m.id = ?1")
	Collection<Survival> findSurvivalByManager(int manager);
}
