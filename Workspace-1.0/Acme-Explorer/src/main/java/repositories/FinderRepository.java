package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Finder;
import domain.Trip;

@Repository
public interface FinderRepository extends JpaRepository<Finder, Integer>{
	
	// 34.1
	@Query("select t from Trip t where (t.title like ?1 or t.description like ?1) and t.tripStart > ?2 and t.tripEnd < ?3 and t.price >= ?4 and t.price <= ?5")
	Collection<Trip> resultFinder(String singleKey, Date start, Date end, Double minPrice, Double maxPrice);
}
