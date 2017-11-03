package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.SpamWords;

@Repository
public interface SpamWordsRepository extends JpaRepository<SpamWords, Integer>{

}
