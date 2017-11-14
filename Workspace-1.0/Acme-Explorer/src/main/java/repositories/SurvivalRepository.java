package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Survival;
import domain.Trip;

@Repository
public interface SurvivalRepository extends JpaRepository<Survival, Integer>{
	
	//44.1
	@Query("select t from Trip t where (select count(a) from t.application a where a.explorer.id = ?1)>0 and (select count(b) from Trip t join t.survival b where b.id = ?2)>0 and t.publication<CURRENT_DATE)")
	Trip enrolSurvivalExplorer(int explorer, int survival);
	
	//43.1
	@Query("select s from Survival s where s.manager = ?1")
	Collection<Survival> findSurvivalByManager(int manager);
}
