package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.PersonalRecordRepository;

@Service
@Transactional
public class PersonalRecordService {
	
	@Autowired
	private PersonalRecordRepository personalRecordRepository;
	
	public PersonalRecordService(){
		super();
	}
}
