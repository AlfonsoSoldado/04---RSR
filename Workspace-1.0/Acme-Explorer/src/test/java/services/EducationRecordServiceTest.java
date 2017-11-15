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
import domain.EducationRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class EducationRecordServiceTest extends AbstractTest {

	// Service under test -------------------------

	@Autowired
	private EducationRecordService educationRecordService;

	// Supporting services -----------------------

	// Test --------------------------------------

	@Test
	public void testCreateEducationRecord() {
		EducationRecord educationRecord;
		educationRecord = this.educationRecordService.create();
		Assert.notNull(educationRecord);
	}

	@Test
	public void testFindAllEducationRecord() {
		Collection<EducationRecord> educationRecords;
		educationRecords = this.educationRecordService.findAll();
		Assert.notNull(educationRecords);
	}

	@Test
	public void testFindOneEducationRecord() {
		EducationRecord educationRecord;
		educationRecord = this.educationRecordService.findOne(super
				.getEntityId("educationRecord1"));
		Assert.notNull(educationRecord);
	}

	@Test
	public void testSaveEducationRecord() {
		EducationRecord educationRecord;
		educationRecord = this.educationRecordService.create();
		educationRecord.setComment("comentario modificado");
		educationRecord.setInstitution("Institución modificada");
		educationRecord.setLink("http://www.institucioneducation.com");
		educationRecord.setTitle("Titulo education");
		this.educationRecordService.save(educationRecord);
	}

	@Test
	public void testDeleteEducationRecord() {
		EducationRecord educationRecord;
		;
		educationRecord = this.educationRecordService.findOne(super
				.getEntityId("educationRecord1"));
		this.educationRecordService.delete(educationRecord);
	}
}
