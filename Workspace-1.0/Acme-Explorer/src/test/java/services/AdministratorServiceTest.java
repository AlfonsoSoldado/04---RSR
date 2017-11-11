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
import domain.Curriculum;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
				"classpath:spring/datasource.xml",
				"classpath:spring/config/packages.xml"})
@Transactional
public class AdministratorServiceTest extends AbstractTest{
	
	// Service under test -------------------------
	
	@Autowired
	private AdministratorService administratorService;
	
	// Supporting services -----------------------
	
	// Test --------------------------------------
	
	@Test
	public void testCreateAdministrator(){
		authenticate("administrator1");
		Administrator administrator;
		administrator = this.administratorService.create();
		Assert.notNull(administrator);
		unauthenticate();
	}
	
	@Test
	public void testFindAllAdministrator(){
		authenticate("administrator1");
		Collection<Administrator> administrators;
		administrators = this.administratorService.findAll();
		Assert.notNull(administrators);
	}
	
	@Test
	public void testFindOneAdministrator(){
		authenticate("administrator1");
		Administrator administrator;
		administrator = this.administratorService.findOne(super.getEntityId("administrator1"));
		Assert.notNull(administrator);
	}
	
	@Test
	public void testSaveAdministrator(){
		this.authenticate("administrator1");
		Administrator administrator;
		administrator = this.administratorService.create();
		administrator.setEmail("jojo@hotmail.com");
		administrator.setAddress("C/ Valencia");
		administrator.setName("Rafael");
		this.administratorService.save(administrator);
		unauthenticate();
	}
	
	@Test
	public void testDeleteAdministrator(){
		authenticate("administrator1");
		Administrator administrator;
		administrator = this.administratorService.findOne(super.getEntityId("administrator1"));
		this.administratorService.delete(administrator);
	}
}
