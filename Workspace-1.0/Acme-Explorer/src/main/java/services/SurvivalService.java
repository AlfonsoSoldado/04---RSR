package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.SurvivalRepository;

@Service
@Transactional
public class SurvivalService {

	@Autowired
	private SurvivalRepository survivalRepository;
	
	public SurvivalService(){
		super();
	}
}
