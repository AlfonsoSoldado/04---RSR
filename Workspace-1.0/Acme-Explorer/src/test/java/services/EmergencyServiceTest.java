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

import domain.Emergency;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class EmergencyServiceTest extends AbstractTest {

	// Service under test -----------------

	@Autowired
	private EmergencyService emergencyService;

	// Supporting service -----------------

	// Test -------------------------------

	@Test
	public void testCreateEmergency() {
		Emergency emergency;
		emergency = this.emergencyService.create();
		Assert.notNull(emergency);
	}

	@Test
	public void testFindAllEmergency() {
		Collection<Emergency> emergencies;
		emergencies = new ArrayList<Emergency>();
		emergencies = this.emergencyService.findAll();
		Assert.notNull(emergencies);
	}

	@Test
	public void testFindOneEmergency() {
		Emergency emergency;
		emergency = this.emergencyService.findOne(super
				.getEntityId("emergency1"));
		Assert.notNull(emergency);
	}

	@Test
	public void testSaveEmergency() {
		Emergency emergency;
		emergency = this.emergencyService.create();

		emergency.setName("SampleEmergency");

		emergency.setEmail("sample@gmail.com");

		emergency.setPhoneNumber("654321234");

		this.emergencyService.save(emergency);

	}

	@Test
	public void testDeleteEmergency() {
		Emergency emergency;
		emergency = this.emergencyService.findOne(super
				.getEntityId("emergency1"));
		this.emergencyService.delete(emergency);
	}
}
