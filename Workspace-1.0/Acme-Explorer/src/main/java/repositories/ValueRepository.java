package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Value;

@Repository
public interface ValueRepository extends JpaRepository<Value, Integer>{

}
