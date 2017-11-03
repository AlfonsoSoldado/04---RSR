package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.ProfesionalRecord;

@Repository
public interface ProfesionalRecordRepository extends JpaRepository<ProfesionalRecord, Integer>{

}
