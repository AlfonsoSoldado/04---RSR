package services;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.PersonalRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class PersonalRecordServiceTest extends AbstractTest {

	// Service under test ------------------

	@Autowired
	private PersonalRecordService personalRecordService;

	// Supporting services -----------------

	// Test --------------------------------

	@Test
	public void testCreatePersonalRecord() {
		PersonalRecord personal;
		personal = this.personalRecordService.create();
		Assert.notNull(personal);
	}

	@Test
	public void testFindAllPersonalRecord() {
		Collection<PersonalRecord> personal;
		personal = new ArrayList<PersonalRecord>();
		personal = this.personalRecordService.findAll();
		Assert.notNull(personal);
	}

	@Test
	public void testFindOnePersonalRecord() {
		PersonalRecord personal;
		personal = this.personalRecordService.findOne(super
				.getEntityId("personalRecord1"));
		Assert.notNull(personal);
	}

	@Test
	public void testSavePersonalRecord() {
		PersonalRecord personal;
		personal = this.personalRecordService.create();

		personal.setName("Juan");

		personal.setPhoto("http://www.google.es");

		personal.setEmail("juan@hotmail.com");

		personal.setPhoneNumber("654789054");

		personal.setLikedln("http://www.likedlin.com");

		this.personalRecordService.save(personal);
	}

	@Test
	public void testDeletePersonalRecord() {
		PersonalRecord personal;
		personal = this.personalRecordService.findOne(super
				.getEntityId("personalRecord2"));
		this.personalRecordService.delete(personal);
	}
}
