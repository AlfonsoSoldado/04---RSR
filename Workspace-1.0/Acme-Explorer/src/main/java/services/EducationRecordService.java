package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.EducationRecordRepository;

@Service
@Transactional
public class EducationRecordService {

	@Autowired
	private EducationRecordRepository educationRecordRepository;
	
	public EducationRecordService(){
		super();
	}
}
