package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Emergency;

@Repository
public interface EmergencyRepository extends JpaRepository<Emergency, Integer>{
	
}
