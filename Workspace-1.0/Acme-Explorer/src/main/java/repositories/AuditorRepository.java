package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Audit;
import domain.Auditor;

@Repository
public interface AuditorRepository extends JpaRepository<Auditor, Integer>{
	
	@Query("select a from Auditor a where a.userAccount.id=?1")
	Auditor findAuditorByUserAccountId(int uA);
	
	
}
