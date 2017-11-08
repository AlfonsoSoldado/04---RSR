package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.LegalTextRepository;

@Service
@Transactional
public class LegalTextService {

	@Autowired
	private LegalTextRepository legalTextRepository;
	
	public LegalTextService(){
		super();
	}
}
