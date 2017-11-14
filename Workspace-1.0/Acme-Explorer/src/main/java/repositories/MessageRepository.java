package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;
import domain.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{
	
	@Query("select a from Actor a, Message m where a=m.sender")
	Actor senderActor();
	
	@Query("select a from Message m join m.recipient a")
	Collection<Actor> recipientActor();
	
	@Query("select m from Message m")
	Collection<Message> findMessages();
	
	//35.1
	@Query("select m from Ranger r join r.sent m where m.spam = true")
	Collection<Message> findRangerMessages();
	
	//35.1
	@Query("select m from Manager ma join ma.sent m where m.spam = true")
	Collection<Message> findManagerMessages();
}
