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
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class AuditServiceTest extends AbstractTest {

	// Service under test ----------------

	@Autowired
	private AuditService auditService;

	// Supporting services ----------------

	@Autowired
	private TripService tripService;

	@Autowired
	private AuditorService auditorService;

	// Test ------------------------------

	@Test
	public void testCreateAudit() {
		authenticate("auditor01");
		Audit audit;
		audit = this.auditService.create();
		Assert.notNull(audit);
		unauthenticate();
	}

	@Test
	public void testFindAllAudit() {
		authenticate("auditor01");
		Collection<Audit> audits;
		audits = this.auditService.findAll();
		Assert.notNull(audits);
		unauthenticate();
	}

	@Test
	public void testFindOneAudit() {
		authenticate("auditor01");
		Audit audit;
		audit = this.auditService.findOne(super.getEntityId("audit1"));
		Assert.notNull(audit);
		unauthenticate();
	}

	@Test
	public void testSaveAudit() {
		authenticate("auditor01");
		Audit audit;
		audit = this.auditService.create();

		Date moment = new Date(System.currentTimeMillis() - 1);
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
		trip = tripService.findOne(super.getEntityId("trip1"));
		audit.setTrip(trip);

		Auditor auditor;
		auditor = this.auditorService.create();
		audit.setAuditor(auditor);

		this.auditorService.save(auditor);
		unauthenticate();
	}

	@Test
	public void testDeleteAudit() {
		authenticate("auditor01");
		Audit audit;
		audit = this.auditService.findOne(super.getEntityId("audit1"));
		this.auditService.delete(audit);
		unauthenticate();
	}
}
