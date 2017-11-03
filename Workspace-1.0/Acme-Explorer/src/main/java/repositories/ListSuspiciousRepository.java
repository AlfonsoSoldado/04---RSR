package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.ListSuspicious;

@Repository
public interface ListSuspiciousRepository extends JpaRepository<ListSuspicious, Integer>{

}
