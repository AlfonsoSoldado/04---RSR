package repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Explorer;

@Repository
public interface ExplorerRepository {
	
	@Query("select e from Explorer e.userAccount.id=?1")
	Explorer findExplorerByUserAccountId(int uA);
	
}
