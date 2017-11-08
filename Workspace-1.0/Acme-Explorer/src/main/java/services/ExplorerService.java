package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.ExplorerRepository;

@Service
@Transactional
public class ExplorerService {

	@Autowired
	private ExplorerRepository explorerRepository;
	
	@Autowired
	private FolderService folderService; 
	
	public ExplorerService(){
		super();
	}
}
