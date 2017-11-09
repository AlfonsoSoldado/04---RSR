package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.ProfessionalRecord;

@Repository
public interface ProfesionalRecordRepository extends JpaRepository<ProfessionalRecord, Integer>{

	
}
