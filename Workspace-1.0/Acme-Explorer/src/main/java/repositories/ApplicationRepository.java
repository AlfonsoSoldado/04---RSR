package repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer>{

	//C-5
	@Query("select cast(count(a) as float)/(select count(a) from Application a) from Application a where a.status='PENDING'")
	Double ApplicationPending();
	
	//C-6
	@Query("select cast(count(a) as float)/(select count(a) from Application a) from Application a where a.status='DUE'")
	Double ApplicationDue();
	
	//C-7
	@Query("select cast(count(a) as float)/(select count(a) from Application a) from Application a where a.status='ACCEPTED'")
	Double ApplicationAccepted();
	
	//C-8
	@Query("select cast(count(a) as float)/(select count(a) from Application a) from Application a where a.status='CANCELLED'")
	Double ApplicationCancelled();
	
}
