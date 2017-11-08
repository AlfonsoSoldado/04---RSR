package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.AuditorRepository;

@Service
@Transactional
public class AuditorService {

	@Autowired
	private AuditorRepository auditorRepository;
	
	@Autowired
	private FolderService folderService; 
	
	public AuditorService(){
		super();
	}
}
