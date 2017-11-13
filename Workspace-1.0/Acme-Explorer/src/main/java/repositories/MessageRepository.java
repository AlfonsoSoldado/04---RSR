package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;
import domain.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{
	
	@Query("select a from Actor a, Message m where a=m.sender")
	Actor senderActor();
	
	@Query("select a from Actor a, Message m where a=m.recipient")
	Actor recipientActor();
}
