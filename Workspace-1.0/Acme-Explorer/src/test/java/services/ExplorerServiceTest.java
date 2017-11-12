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
import domain.Explorer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
				"classpath:spring/datasource.xml",
				"classpath:spring/config/packages.xml"})
@Transactional
public class ExplorerServiceTest extends AbstractTest{

	// Service under test -------------------------
	
	@Autowired
	private ExplorerService explorerService;
	
	// Supporting services -----------------------

			
	// Test --------------------------------------
	
	@Test
	public void testCreateExplorer() {
		Explorer explorer;
		explorer = this.explorerService.create();
		Assert.notNull(explorer);
	}
	
	@Test
	public void testFindAllExplorer() {
		Collection<Explorer> explorers;
		explorers = this.explorerService.findAll();
		Assert.notNull(explorers);
	}
	
	@Test
	public void testFindOneExplorer() {
		Explorer explorer;
		explorer = this.explorerService.findOne(super.getEntityId("explorer1"));
		Assert.notNull(explorer);
	}
	
	@Test
	public void testSaveExplorer() {
		Explorer explorer;
		explorer = this.explorerService.create();
		
		explorer.setAddress("C/ Barcelona");
		explorer.setEmail("explorernuevo@gmail.com");
		explorer.setName("ExplorerNuevo");
		explorer.setPhoneNumber("655112233");
		explorer.setSurname("García");

		this.explorerService.save(explorer);
	}
	
	@Test
	public void testDeleteExplorer() {
		Explorer explorer;
		explorer = this.explorerService.findOne(super.getEntityId("explorer1"));
		this.explorerService.delete(explorer);
	}
}
