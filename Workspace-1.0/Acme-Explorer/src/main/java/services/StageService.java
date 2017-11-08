package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.StageRepository;

@Service
@Transactional
public class StageService {

	@Autowired
	private StageRepository stageRepository;
	
	public StageService(){
		super();
	}
}
