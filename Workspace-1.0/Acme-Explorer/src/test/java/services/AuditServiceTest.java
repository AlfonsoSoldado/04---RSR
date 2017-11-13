package services;

import java.util.ArrayList;
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
import domain.Audit;
import domain.Auditor;
import domain.Trip;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
				"classpath:spring/datasource.xml",
				"classpath:spring/config/packages.xml"})
@Transactional
public class AuditServiceTest extends AbstractTest{
	
	// Service under test ----------------
	
	@Autowired
	private AuditService auditService;
	
	
	
	// Supporting services ----------------
	
	private TripService tripService;
	private AuditorService auditorService;
	
	
	
	// Test ------------------------------
	
	@Test 
	public void testCreateAudit(){
		Audit audit;
		audit = this.auditService.create();
		Assert.notNull(audit);
	}
	
	@Test 
	public void testFindAllAudit(){
		Collection<Audit> audits;
		audits = this.auditService.findAll();
		Assert.notNull(audits);
		
	}
	
	@Test
	public void testFindOneAudit(){
		Audit audit;
		audit = this.auditService.findOne(super.getEntityId("audit1"));
		Assert.notNull(audit);
	}

	@Test
	public void testSaveAudit(){
		Audit audit;
		audit = this.auditService.create();
		

		Date moment = new Date(System.currentTimeMillis() -1 );
		audit.setMoment(moment);
		
		audit.setTitle("Audit 1");
		
		audit.setDescription("Sample title");
		
		Collection<String> attachment = new ArrayList<String>();
		attachment.add("Att1");
		attachment.add("Att2");
		attachment.add("Att3");
		audit.setAttachment(attachment);
		
		audit.setDraftMode(true);
		
		Trip trip;
		trip = this.tripService.create();
		audit.setTrip(trip);
		
		Auditor auditor;
		auditor = this.auditorService.create();
		audit.setAuditor(auditor);
		
	}
	
	@Test
	public void testDeleteAudit(){
		Audit audit;
		audit = this.auditService.findOne(super.getEntityId("audit1"));
		this.auditService.delete(audit);
	}
	
	
	
	
	
	
	
	
	
	
	
}
