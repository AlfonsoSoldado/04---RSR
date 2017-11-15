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
import domain.EndorserRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class EndorserRecordServiceTest extends AbstractTest {

	// Service under test -------------------------

	@Autowired
	private EndorserRecordService endorserRecordService;

	// Supporting services -----------------------

	// Test --------------------------------------

	@Test
	public void testCreateEndorserRecord() {
		EndorserRecord endorserRecord;
		endorserRecord = this.endorserRecordService.create();
		Assert.notNull(endorserRecord);
	}

	@Test
	public void testFindAllEndorserRecord() {
		Collection<EndorserRecord> endorserRecords;
		endorserRecords = this.endorserRecordService.findAll();
		Assert.notNull(endorserRecords);
	}

	@Test
	public void testFindOneEndorserRecord() {
		EndorserRecord endorserRecord;
		endorserRecord = this.endorserRecordService.findOne(super
				.getEntityId("endorserRecord1"));
		Assert.notNull(endorserRecord);
	}

	@Test
	public void testSaveEndorserRecord() {
		EndorserRecord endorserRecord;
		endorserRecord = this.endorserRecordService.create();
		endorserRecord.setComment("Otro comentario");
		endorserRecord.setEmail("nuevoemail@gmail.com");
		endorserRecord.setEndorserName("Nombre endorser");
		endorserRecord.setLikedln("http://www.linkedin.com");
		this.endorserRecordService.save(endorserRecord);
	}

	@Test
	public void testDeleteEndorserRecord() {
		EndorserRecord endorserRecord;
		;
		endorserRecord = this.endorserRecordService.findOne(super
				.getEntityId("endorserRecord1"));
		this.endorserRecordService.delete(endorserRecord);
	}
}
