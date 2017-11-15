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
import domain.Audit;
import domain.Auditor;
import domain.Note;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class AuditorServiceTest extends AbstractTest {

	// Service under test -------------------------

	@Autowired
	private AuditorService auditorService;

	// Supporting services -----------------------

	@Autowired
	private NoteService noteService;

	@Autowired
	private AuditService auditService;

	// Test --------------------------------------

	@Test
	public void testCreateAuditor() {
		Auditor auditor;
		auditor = this.auditorService.create();
		Assert.notNull(auditor);
	}

	@Test
	public void testFindAllAuditor() {
		Collection<Auditor> auditors;
		auditors = this.auditorService.findAll();
		Assert.notNull(auditors);
	}

	@Test
	public void testFindOneAuditor() {
		Auditor auditor;
		auditor = this.auditorService.findOne(super.getEntityId("auditor1"));
		Assert.notNull(auditor);
	}

	@Test
	public void testSaveAuditor() {
		authenticate("auditor01");
		Auditor auditor;
		auditor = this.auditorService.create();

		Note note;
		Audit audit;
		Collection<Note> notes = new ArrayList<Note>();
		Collection<Audit> audits = new ArrayList<Audit>();

		note = this.noteService.findOne(super.getEntityId("note1"));
		audit = this.auditService.findOne(super.getEntityId("audit1"));
		notes.add(note);
		audits.add(audit);

		auditor = this.auditorService.findOne(super.getEntityId("auditor1"));

		auditor.setNote(notes);
		auditor.setAudit(audits);
		auditor.setName("Pepito");
		auditor.setSurname("Martos");
		auditor.setEmail("pepito@hotmail.com");
		auditor.setAddress("C/Rosa");

		this.auditorService.save(auditor);
		unauthenticate();
	}

	@Test
	public void testDeleteAuditor() {
		Auditor auditor;
		auditor = this.auditorService.findOne(super.getEntityId("auditor1"));
		this.auditorService.delete(auditor);
	}

	@Test
	public void testFindByPrincipal() {
		authenticate("auditor01");
		Auditor auditor;
		auditor = this.auditorService.findByPrincipal();
		Assert.notNull(auditor);
		unauthenticate();
	}
}
