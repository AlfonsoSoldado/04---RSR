package repositories;


import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer>{

	//13.2
	@Query("select e.application from Explorer e join e.application a where a.status like 'ACCEPTED'")
	Collection<Application> findApplicationExplorer();
}
