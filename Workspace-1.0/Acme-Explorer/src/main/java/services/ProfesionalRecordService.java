package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.ProfesionalRecordRepository;

@Service
@Transactional
public class ProfesionalRecordService {
	
	@Autowired
	private ProfesionalRecordRepository profesionalRecordRepository;
	
	public ProfesionalRecordService(){
		super();
	}
}
