package repositories;


import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Manager;
import domain.Ranger;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer>{
	
	@Query("select m from Manager m where m.userAccount.id=?1")
	Manager findManagerByUserAccountId(int uA);
	
	//35.2
	@Query("select m from Manager m where m.suspicious = true")
	Collection<Ranger> banManager();
		
	//35.2
	@Query("select m from Manager m where m.suspicious = false")
	Collection<Ranger> unbanManager();
}
