package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.EmergencyRepository;

@Service
@Transactional
public class EmergencyService {

	@Autowired
	private EmergencyRepository emergencyRepository;
	
	public EmergencyService(){
		super();
	}
}
