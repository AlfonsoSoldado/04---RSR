package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.FolderRepository;

@Service
@Transactional
public class FolderService {

	@Autowired
	private FolderRepository folderRepository;
	
	public FolderService(){
		super();
	}
}
