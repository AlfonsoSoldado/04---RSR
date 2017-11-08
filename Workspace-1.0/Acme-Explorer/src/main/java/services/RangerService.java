package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.RangerRepository;

@Service
@Transactional
public class RangerService {

	@Autowired
	private RangerRepository rangerRepository;
	
	@Autowired
	private FolderService folderService; 
	
	public RangerService(){
		super();
	}
}
