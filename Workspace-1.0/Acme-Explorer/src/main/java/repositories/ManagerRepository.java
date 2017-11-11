package repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer>{
	
	@Query("select m from Manager m where m.userAccount.id=?1")
	Manager findManagerByUserAccountId(int uA);
	
	
	
}
