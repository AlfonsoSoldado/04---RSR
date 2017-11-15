package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

	// 11.3
	@Query("select m from Message m")
	Collection<Message> findMessages();

	// 35.1
	@Query("select m from Ranger r join r.sent m where m.spam = true")
	Collection<Message> findRangerMessages();

	// 35.1
	@Query("select m from Manager ma join ma.sent m where m.spam = true")
	Collection<Message> findManagerMessages();
}
