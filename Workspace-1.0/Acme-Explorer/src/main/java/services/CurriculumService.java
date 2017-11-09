package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CurriculumRepository;
import domain.Curriculum;

@Service
@Transactional
public class CurriculumService {
	
	// Managed repository

	@Autowired
	private CurriculumRepository curriculumRepository;
	
	// Supporting services
	
	// Constructors
	
	public CurriculumService(){
		super();
	}
	
	// Simple CRUD methods
	
	public Collection<Curriculum> findAll() {
		Collection<Curriculum> res;
		res = this.curriculumRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	
	public Curriculum findOne(int curriculum) {
		Assert.isTrue(curriculum != 0);
		Curriculum res;
		res = this.curriculumRepository.findOne(curriculum);
		Assert.notNull(res);
		return res;
	}
	
	public Curriculum save(Curriculum curriculum) {
		Assert.notNull(curriculum);
		Curriculum res;
		res = this.curriculumRepository.save(curriculum);
		return res;
	}
	
	public void delete(Curriculum curriculum) {
		Assert.notNull(curriculum);
		Assert.isTrue(curriculum.getId() != 0);
		Assert.isTrue(this.curriculumRepository.exists(curriculum.getId()));
		this.curriculumRepository.delete(curriculum);
	}
	
	// Other business methods
	
}
