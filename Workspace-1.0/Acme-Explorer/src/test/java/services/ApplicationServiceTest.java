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
import domain.Application;
import domain.Curriculum;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
				"classpath:spring/datasource.xml",
				"classpath:spring/config/packages.xml"})
@Transactional
public class ApplicationServiceTest extends AbstractTest {

	// Service under test -------------------------
	
	@Autowired
	private ApplicationService applicationService;
			
	// Supporting services -----------------------
			
			
	// Test --------------------------------------
	
	@Test
	public void testCreateApplication() {
		authenticate("explorer01");
		Application application;
		application = this.applicationService.create();
		Assert.notNull(application);
		unauthenticate();
	}
	
	@Test
	public void testFindAllApplication() {
		authenticate("explorer01");
		Collection<Application> applications;
		applications = this.applicationService.findAll();
		Assert.notNull(applications);
	}
}
