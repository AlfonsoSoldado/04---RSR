package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.ActorRepository;

@Service
@Transactional
public class ActorService {
	
	@Autowired
	private ActorRepository actorRepository;
	
	@Autowired
	private FolderService folderService;
	
	public ActorService(){
		super();
	}
}
