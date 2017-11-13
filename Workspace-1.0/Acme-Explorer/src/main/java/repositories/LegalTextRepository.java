package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.LegalText;

@Repository
public interface LegalTextRepository extends JpaRepository<LegalText, Integer> {

	// 14.2
	@Query("select l from LegalText l where l.draftMode = true and l.legalText = ?1")
	Collection<LegalText> findLegalTextDraftTrue(int id);

}
