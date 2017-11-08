package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.SponsorRepository;

@Service
@Transactional
public class SponsorService {

	@Autowired
	private SponsorRepository sponsorRepository;
	
	@Autowired
	private FolderService folderService; 
	
	public SponsorService(){
		super();
	}
}
