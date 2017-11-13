package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CurriculumRepository;
import domain.Curriculum;
import domain.EducationRecord;
import domain.EndorserRecord;
import domain.MiscellaneousRecord;
import domain.PersonalRecord;
import domain.ProfessionalRecord;
import domain.Ranger;

@Service
@Transactional
public class CurriculumService {
	
	// Managed repository

	@Autowired
	private CurriculumRepository curriculumRepository;
	
	// Supporting services
	
	@Autowired
	private RangerService rangerService;
	
	// Constructors
	
	public CurriculumService(){
		super();
	}
	
	// Simple CRUD methods
	
	public Curriculum create() {
		Curriculum res = new Curriculum();
		Collection<ProfessionalRecord> professionalRecord = new ArrayList<ProfessionalRecord>();
		Collection<EducationRecord> educationRecord = new ArrayList<EducationRecord>();
		Collection<EndorserRecord> endorserRecord = new ArrayList<EndorserRecord>();
		Collection<MiscellaneousRecord> miscellaneousRecord = new ArrayList<MiscellaneousRecord>();
		PersonalRecord personalRecord = new PersonalRecord();
		
		res.setProfessionalRecord(professionalRecord);
		res.setEducationRecord(educationRecord);
		res.setEndorserRecord(endorserRecord);
		res.setMiscellaneousRecord(miscellaneousRecord);
		res.setPersonalRecord(personalRecord);
		
		return res;
	}
	
	public Collection<Curriculum> findAll() {
		rangerService.checkAuthority();
		
		Collection<Curriculum> res;
		res = this.curriculumRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	
	public Curriculum findOne(int curriculum) {
		rangerService.checkAuthority();
		
		Assert.isTrue(curriculum != 0);
		Curriculum res;
		res = this.curriculumRepository.findOne(curriculum);
		Assert.notNull(res);
		return res;
	}
	
	public Curriculum save(Curriculum curriculum) {
		rangerService.checkAuthority();
		
		Ranger ranger;
		ranger = this.rangerService.findByPrincipal();
		
		Assert.notNull(curriculum);
		Assert.notNull(curriculum.getPersonalRecord());
		
		Curriculum res;
		res = this.curriculumRepository.save(curriculum);
		
		ranger.setCurriculum(res);
		
		return res;
	}
	
	public void delete(Curriculum curriculum) {
		rangerService.checkAuthority();
		
		Assert.notNull(curriculum);
		Assert.isTrue(curriculum.getId() != 0);
		Assert.isTrue(this.curriculumRepository.exists(curriculum.getId()));
		
		this.curriculumRepository.delete(curriculum);
	}
	
	// Other business methods
	
}
