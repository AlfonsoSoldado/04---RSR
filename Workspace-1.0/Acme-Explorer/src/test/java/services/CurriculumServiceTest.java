package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Curriculum;
import domain.PersonalRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class CurriculumServiceTest extends AbstractTest {

	// Service under test -------------------------

	@Autowired
	private CurriculumService curriculumService;

	// Supporting services -----------------------

	@Autowired
	private PersonalRecordService personalRecordService;

	// Test --------------------------------------

	@Test
	public void testCreateCurriculum() {
		authenticate("ranger01");
		Curriculum curriculum;
		curriculum = this.curriculumService.create();
		Assert.notNull(curriculum);
		unauthenticate();
	}

	@Test
	public void testFindAllCurriculum() {
		authenticate("ranger01");
		Collection<Curriculum> curriculums;
		curriculums = this.curriculumService.findAll();
		Assert.notNull(curriculums);
	}

	@Test
	public void testFindOneCurriculum() {
		authenticate("ranger01");
		Curriculum curriculum;
		curriculum = this.curriculumService.findOne(super
				.getEntityId("curriculum1"));
		Assert.notNull(curriculum);
	}

	@Test
	public void testSaveCurriculum() {
		this.authenticate("ranger01");
		Curriculum curriculum;
		curriculum = this.curriculumService.create();
		PersonalRecord personalRecord = this.personalRecordService.create();
		personalRecord.setName("Manolito");
		personalRecord.setPhoto("http://www.google.es/");
		personalRecord.setEmail("monolito1@gmail.com");
		personalRecord.setPhoneNumber("694444000");
		personalRecord.setLikedln("http://www.linkedin.com/");
		PersonalRecord res = this.personalRecordService.save(personalRecord);
		curriculum.setPersonalRecord(res);
		curriculum.setTicker("253885-HYAH");
		this.curriculumService.save(curriculum);
		unauthenticate();
	}

	@Test
	public void testDeleteCurriculum() {
		authenticate("ranger01");
		Curriculum curriculum;
		curriculum = this.curriculumService.findOne(super
				.getEntityId("curriculum1"));
		this.curriculumService.delete(curriculum);
	}
}
