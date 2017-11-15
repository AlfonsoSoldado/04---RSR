package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {

	// 33
	@Query("select n from Note n where n.auditor.id = ?1")
	Collection<Note> findNotesByAuditor(int id);
}
