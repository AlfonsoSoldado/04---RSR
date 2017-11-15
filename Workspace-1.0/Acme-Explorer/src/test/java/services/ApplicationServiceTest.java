package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Application;
import domain.CC;
import domain.Explorer;
import domain.Manager;
import domain.Trip;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class ApplicationServiceTest extends AbstractTest {

	// Service under test -------------------------

	@Autowired
	private ApplicationService applicationService;

	// Supporting services -----------------------

	@Autowired
	private ExplorerService explorerService;

	@Autowired
	private ManagerService managerService;

	@Autowired
	private TripService tripService;

	// Test --------------------------------------

	// Application explorer

	@Test
	public void testCreateApplicationExplorer() {
		authenticate("explorer01");
		Application application;
		application = this.applicationService.create();
		Assert.notNull(application);
		unauthenticate();
	}

	@Test
	public void testFindAllApplicationExplorer() {
		authenticate("explorer01");
		Collection<Application> applications;
		applications = this.applicationService.findAll();
		Assert.notNull(applications);
	}

	@Test
	public void testFindOneApplicationExplorer() {
		authenticate("explorer01");
		Application application;
		application = this.applicationService.findOne(super
				.getEntityId("application1"));
		Assert.notNull(application);
	}

	@Test
	public void testSaveApplicationExplorer() {
		this.authenticate("explorer01");
		Application application;
		application = this.applicationService.create();

		Trip trip;
		Manager manager;
		Explorer explorer;

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2017);
		cal.set(Calendar.MONTH, 2);
		cal.set(Calendar.DAY_OF_MONTH, 12);
		Date moment = cal.getTime();
		String status = "ACCEPTED";
		String comment = "Comment";
		String reason = "Reason";
		CC cc = new CC();
		cc.setHolderName("BBVA");
		cc.setBrandName("MasterCard");
		cc.setNumber("4099537775843795");
		cc.setExpirationMonth(02);
		cc.setExpirationYear(2019);
		cc.setCVV(123);

		trip = this.tripService.findOne(super.getEntityId("trip1"));
		manager = this.managerService.findOne(super.getEntityId("manager1"));
		explorer = this.explorerService.findOne(super.getEntityId("explorer1"));

		application.setMoment(moment);
		application.setStatus(status);
		application.setComment(comment);
		application.setReason(reason);
		application.setCreditCard(cc);
		application.setTrip(trip);
		application.setManager(manager);
		application.setExplorer(explorer);

		this.applicationService.save(application);
		unauthenticate();
	}

	@Test
	public void testDeleteApplicationExplorer() {
		authenticate("explorer01");
		Application application;
		application = this.applicationService.findOne(super
				.getEntityId("application1"));
		this.applicationService.delete(application);
	}

	// Application manager

	@Test
	public void testCreateApplicationManager() {
		authenticate("manager01");
		Application application;
		application = this.applicationService.create();
		Assert.notNull(application);
		unauthenticate();
	}

	@Test
	public void testFindAllApplicationManager() {
		authenticate("manager01");
		Collection<Application> applications;
		applications = this.applicationService.findAll();
		Assert.notNull(applications);
	}

	@Test
	public void testFindOneApplicationManager() {
		authenticate("manager01");
		Application application;
		application = this.applicationService.findOne(super
				.getEntityId("application1"));
		Assert.notNull(application);
	}

	@Test
	public void testSaveApplicationManager() {
		authenticate("manager01");
		Application application;
		application = this.applicationService.create();

		Trip trip;
		Manager manager;
		Explorer explorer;

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2017);
		cal.set(Calendar.MONTH, 2);
		cal.set(Calendar.DAY_OF_MONTH, 12);
		Date moment = cal.getTime();
		String status = "ACCEPTED";
		String comment = "Comment";
		String reason = "Reason";
		CC cc = new CC();
		cc.setHolderName("BBVA");
		cc.setBrandName("MasterCard");
		cc.setNumber("4099537775843795");
		cc.setExpirationMonth(02);
		cc.setExpirationYear(2019);
		cc.setCVV(123);

		trip = this.tripService.findOne(super.getEntityId("trip1"));
		manager = this.managerService.findOne(super.getEntityId("manager1"));
		explorer = this.explorerService.findOne(super.getEntityId("explorer1"));

		application.setMoment(moment);
		application.setStatus(status);
		application.setComment(comment);
		application.setReason(reason);
		application.setCreditCard(cc);
		application.setTrip(trip);
		application.setManager(manager);
		application.setExplorer(explorer);

		this.applicationService.save(application);
		unauthenticate();
	}

	@Test
	public void testDeleteApplicationManager() {
		authenticate("manager01");
		Application application;
		application = this.applicationService.findOne(super
				.getEntityId("application1"));
		this.applicationService.delete(application);
	}

	@Test
	public void testChangingStatus() {
		authenticate("manager01");
		Application a;
		a = this.applicationService.findOne(super.getEntityId("application1"));
		Assert.notNull(a);

		String status;
		status = "REJECTED";
		Assert.notNull(status);

		this.applicationService.changingStatus(a, status);
		unauthenticate();
	}

	@Test
	public void testFindListApplication() {
		authenticate("manager01");
		Manager manager;
		manager = this.managerService.findOne(super.getEntityId("manager1"));
		Assert.notNull(manager);

		Collection<Application> res = new ArrayList<Application>();
		res = this.applicationService.findListApplication(manager);
		Assert.notNull(res);
		unauthenticate();
	}

	@Test
	public void testFindApplicationByExplorer() {
		authenticate("explorer01");
		Explorer explorer;
		explorer = this.explorerService.findOne(super.getEntityId("explorer1"));
		Assert.notNull(explorer);

		Collection<Application> res = new ArrayList<Application>();
		res = this.applicationService.findApplicationByExplorer(explorer);
		Assert.notNull(res);
		unauthenticate();
	}

	@Test
	public void testApplicationAccepted() {
		authenticate("explorer01");
		CC cc;
		cc = new CC();
		cc.setHolderName("BBVA");
		cc.setBrandName("MasterCard");
		cc.setNumber("4099537775843795");
		cc.setExpirationMonth(02);
		cc.setExpirationYear(2019);
		cc.setCVV(123);
		Assert.notNull(cc);

		Application application;
		application = this.applicationService.findOne(super
				.getEntityId("application2"));
		Assert.notNull(application);

		this.applicationService.applicationAccepted(cc, application);
		unauthenticate();
	}
}
