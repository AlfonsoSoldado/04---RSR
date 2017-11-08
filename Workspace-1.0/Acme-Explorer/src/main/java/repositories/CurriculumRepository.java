package repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Curriculum;

@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum, Integer>{
	
	//B-5
	@Query("select count(c)/((select count(c2) from Curriculum c2)+0.0) from Curriculum c where c.endorserRecord.size>0")
	Double ratioRangerEndorser();
	
}
