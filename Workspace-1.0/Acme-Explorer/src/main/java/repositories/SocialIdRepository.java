package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.SocialId;

@Repository
public interface SocialIdRepository extends JpaRepository<SocialId, Integer>{

}
