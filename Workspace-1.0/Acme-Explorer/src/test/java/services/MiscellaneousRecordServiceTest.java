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
import domain.MiscellaneousRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class MiscellaneousRecordServiceTest extends AbstractTest {

	// Service under test -------------------------

	@Autowired
	private MiscellaneousRecordService miscellaneousRecordService;

	// Supporting services -----------------------

	// Test --------------------------------------

	@Test
	public void testCreateMiscellaneousRecord() {
		MiscellaneousRecord miscellaneousRecord;
		miscellaneousRecord = this.miscellaneousRecordService.create();
		Assert.notNull(miscellaneousRecord);
	}

	@Test
	public void testFindAllMiscellaneousRecord() {
		Collection<MiscellaneousRecord> miscellaneousRecords;
		miscellaneousRecords = this.miscellaneousRecordService.findAll();
		Assert.notNull(miscellaneousRecords);
	}

	@Test
	public void testFindOneMiscellaneousRecord() {
		MiscellaneousRecord miscellaneousRecord;
		miscellaneousRecord = this.miscellaneousRecordService.findOne(super
				.getEntityId("miscellaneousRecord1"));
		Assert.notNull(miscellaneousRecord);
	}

	@Test
	public void testSaveMiscellaneousRecord() {
		MiscellaneousRecord miscellaneousRecord;
		miscellaneousRecord = this.miscellaneousRecordService.create();
		miscellaneousRecord.setComment("comentario nuevo");
		miscellaneousRecord.setTitle("Titulo miscellaneous");
		miscellaneousRecord.setLink("http://www.miscellaneous.com");
		this.miscellaneousRecordService.save(miscellaneousRecord);
	}

	@Test
	public void testDeleteMiscellaneousRecord() {
		MiscellaneousRecord miscellaneousRecord;
		;
		miscellaneousRecord = this.miscellaneousRecordService.findOne(super
				.getEntityId("miscellaneousRecord1"));
		this.miscellaneousRecordService.delete(miscellaneousRecord);
	}
}
