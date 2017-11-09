package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Explorer;

@Repository
<<<<<<< HEAD
public interface ExplorerRepository extends JpaRepository<Explorer, Integer>{
=======
public interface ExplorerRepository extends JpaRepository<Explorer, Integer> {
>>>>>>> 8c4fde302c18ea9eee9b6accf826915979fe34d1
	
	@Query("select e from Explorer e.userAccount.id=?1")
	Explorer findExplorerByUserAccountId(int uA);
	
}
