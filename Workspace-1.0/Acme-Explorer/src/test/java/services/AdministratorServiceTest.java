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
import domain.Administrator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class AdministratorServiceTest extends AbstractTest {

	// Service under test -------------------------

	@Autowired
	private AdministratorService administratorService;

	// Supporting services -----------------------

	// Test --------------------------------------

	@Test
	public void testCreateAdministrator() {
		authenticate("admin");
		Administrator administrator;
		administrator = this.administratorService.create();
		Assert.notNull(administrator);
		unauthenticate();
	}

	@Test
	public void testFindAllAdministrator() {
		Collection<Administrator> administrators;
		administrators = this.administratorService.findAll();
		Assert.notNull(administrators);
	}

	@Test
	public void testFindOneAdministrator() {
		Administrator administrator;
		administrator = this.administratorService.findOne(super
				.getEntityId("administrator1"));
		Assert.notNull(administrator);
	}

	@Test
	public void testSaveAdministrator() {
		this.authenticate("admin");
		Administrator administrator;
		administrator = this.administratorService.create();
		administrator.setEmail("prueba@gmail.com");
		administrator.setAddress("Av. Valencia");
		administrator.setName("Rafael");
		this.administratorService.save(administrator);
		unauthenticate();
	}

	@Test
	public void testDeleteAdministrator() {
		Administrator administrator;
		administrator = this.administratorService.findOne(super
				.getEntityId("administrator1"));
		this.administratorService.delete(administrator);
	}

	@Test
	public void testApplicationPending() {
		Double res;
		res = this.administratorService.applicationPending();
		Assert.notNull(res);

	}

	@Test
	public void testApplicationDue() {
		Double res;
		res = this.administratorService.applicationDue();
		Assert.notNull(res);
	}

	@Test
	public void testApplicationAccepted() {
		Double res;
		res = this.administratorService.applicationAccepted();
		Assert.notNull(res);
	}

	@Test
	public void testApplicationCancelled() {
		Double res;
		res = this.administratorService.applicationCancelled();
		Assert.notNull(res);

	}

	@Test
	public void ratioRangerEndorser() {
		Double res;
		res = this.administratorService.ratioRangerEndorser();
		Assert.notNull(res);
	}

	@Test
	public void avgMinMaxSqrtManager() {
		Object[] res;
		res = this.administratorService.avgMinMaxSqtrManager();
		Assert.notNull(res);
	}

	@Test
	public void testFindByPrincipal() {
		authenticate("admin");
		Administrator administrator;
		administrator = this.administratorService.findByPrincipal();
		Assert.notNull(administrator);
		unauthenticate();
	}
}
