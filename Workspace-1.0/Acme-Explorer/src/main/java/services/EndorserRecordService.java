package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.EndorserRecordRepository;

@Service
@Transactional
public class EndorserRecordService {

	@Autowired
	private EndorserRecordRepository endorserRecordRepository;
	
	public EndorserRecordService(){
		super();
	}
}
