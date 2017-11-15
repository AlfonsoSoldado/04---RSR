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
import domain.ProfessionalRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class ProfessionalRecordServiceTest extends AbstractTest {

	// Service under test -------------------------

	@Autowired
	private ProfessionalRecordService professionalRecordService;

	// Supporting services -----------------------

	// Test --------------------------------------

	@Test
	public void testCreateProfessionalRecord() {
		ProfessionalRecord professionalRecord;
		professionalRecord = this.professionalRecordService.create();
		Assert.notNull(professionalRecord);
	}

	@Test
	public void testFindAllProfessionalRecord() {
		Collection<ProfessionalRecord> professionalRecords;
		professionalRecords = this.professionalRecordService.findAll();
		Assert.notNull(professionalRecords);
	}

	@Test
	public void testFindOneProfessionalRecord() {
		ProfessionalRecord professionalRecord;
		professionalRecord = this.professionalRecordService.findOne(super
				.getEntityId("professionalRecord1"));
		Assert.notNull(professionalRecord);
	}

	@Test
	public void testSaveProfessionalRecord() {
		ProfessionalRecord professionalRecord;
		professionalRecord = this.professionalRecordService.create();
		professionalRecord.setComment("comentario profesional");
		professionalRecord.setCompanyName("DP Company");
		professionalRecord.setRol("Director de Marketing");
		professionalRecord.setLink("http://www.dp.com");
		this.professionalRecordService.save(professionalRecord);
	}

	@Test
	public void testDeleteProfessionalRecord() {
		ProfessionalRecord professionalRecord;
		;
		professionalRecord = this.professionalRecordService.findOne(super
				.getEntityId("professionalRecord1"));
		this.professionalRecordService.delete(professionalRecord);
	}
}
