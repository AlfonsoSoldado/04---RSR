package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Story;
import domain.Trip;

@Repository
public interface StoryRepository extends JpaRepository<Story, Integer> {

	// 44.2
	@Query("select t from Trip t join t.application a where a.status = 'ACCEPTED' and t.tripEnd < CURRENT_DATE")
	Collection<Trip> findTripsForStory();
}
