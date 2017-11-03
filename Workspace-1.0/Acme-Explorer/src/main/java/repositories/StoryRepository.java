package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Story;

@Repository
public interface StoryRepository extends JpaRepository<Story, Integer>{

}
