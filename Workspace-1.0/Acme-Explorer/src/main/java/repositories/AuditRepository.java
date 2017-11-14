package repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Audit;

@Repository
public interface AuditRepository extends JpaRepository<Audit, Integer>{
	
	//33.2
	@Query("select a from Audit a where a.draftMode = true and a.auditor = ?1")
	Audit findAuditDraftTrue(int id);	
}
