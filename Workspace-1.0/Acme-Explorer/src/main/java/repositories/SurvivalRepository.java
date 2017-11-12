package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Survival;

@Repository
public interface SurvivalRepository extends JpaRepository<Survival, Integer>{

	//43.1: listing
	@Query("select a from Manager m join m.survival s where m.id = ?1")
	Collection<Survival> findSurvivalByManager(int id);
	
}
